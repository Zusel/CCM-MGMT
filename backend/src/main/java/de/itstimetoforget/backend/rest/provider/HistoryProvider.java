package de.itstimetoforget.backend.rest.provider;

import de.itstimetoforget.backend.rest.entity.Customer;
import de.itstimetoforget.backend.rest.entity.Employee;
import de.itstimetoforget.backend.rest.entity.History;
import de.itstimetoforget.backend.rest.entity.Order;
import de.itstimetoforget.backend.rest.service.EmployeeService;
import io.micrometer.core.aop.CountedAspect;
import javassist.NotFoundException;
import org.javers.core.Changes;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Change;
import org.javers.core.diff.changetype.InitialValueChange;
import org.javers.core.diff.changetype.NewObject;
import org.javers.core.diff.changetype.ValueChange;
import org.javers.repository.jql.QueryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class HistoryProvider {

    private Javers javers;
    private EmployeeService employeeService;

    public HistoryProvider(Javers javers, EmployeeService employeeService) {
        this.javers = javers;
        this.employeeService = employeeService;
    }

    public List<History> getCustomerHistory(Long id) {
        Customer customer = new Customer();
        customer.setId(id);
        return getHistoryListFromChanges(javers.findChanges(QueryBuilder.byInstance(customer).build()));
    }

    public List<History> getCompleteHistory() {
        return getHistoryListFromChanges(javers.findChanges(QueryBuilder.anyDomainObject().build()));
    }

    public List<History> getHistoryByClass(String clazz, Long id) throws NotFoundException {
        Object search = null;
        switch (clazz) {
            case "employee":
                search = new Employee();
                ((Employee) search).setId(id);
                break;
            case "customer":
                search = new Customer();
                ((Customer) search).setId(id);
                break;
            case "order":
                search = new Order();
                ((Order) search).setId(id);
                break;
            default:
                throw new NotFoundException("Unknown type!");
        }
        Changes changes;
        if (id == null) {
            changes = javers.findChanges(QueryBuilder.byClass(search.getClass()).build());
        } else {
            changes = javers.findChanges(QueryBuilder.byInstance(search).build());
        }
        return getHistoryListFromChanges(changes);
    }

    private List<History> getHistoryListFromChanges(Changes changes) {
        List<History> histories = new ArrayList<>();
        for (Change change : changes) {
            histories.add(getHistoryFromChange(change));
        }
        histories.sort(Comparator.comparing(History::getDate, Comparator.reverseOrder()));
        return histories;
    }

    private History getHistoryFromChange(Change change) {
        History history = new History();
        history.setEmployee(employeeService.getEmployeeById(Long.valueOf(change.getCommitMetadata().get().getAuthor())));
        history.setDate(change.getCommitMetadata().get().getCommitDate());
        history.setId(change.getCommitMetadata().get().getId().getMajorId());


        Class clazz = change.getClass();
        if (clazz.equals(InitialValueChange.class)) {

            InitialValueChange initialValueChange = (InitialValueChange) change;
            history.setOldValue(String.valueOf(initialValueChange.getLeft()));
            history.setNewValue(String.valueOf(initialValueChange.getRight()));
            history.setChangeType("InitialValueChange");
            history.setFieldChanged(initialValueChange.getPropertyName());

        } else if (clazz.equals(NewObject.class)) {
            history.setChangeType("NewObject");
        } else if (clazz.equals(ValueChange.class)) {

            ValueChange valueChange = (ValueChange) change;
            history.setOldValue((String) valueChange.getLeft());
            history.setNewValue((String) valueChange.getRight());
            history.setFieldChanged(valueChange.getPropertyName());
            history.setChangeType("Value Changed");
        }
        return history;
    }
}
