package de.itstimetoforget.backend.rest.provider;

import de.itstimetoforget.backend.rest.entity.Customer;
import org.javers.core.Changes;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.repository.jql.QueryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryProvider {

    private Javers javers;

    public HistoryProvider(Javers javers) {
        this.javers = javers;
    }

    public Changes getCustomerHistory(Long id) {
        Customer customer = new Customer();
        customer.setId(id);
        return javers.findChanges(QueryBuilder.byInstance(customer).build());
    }

    public Changes getCompleteHistory() {
        return javers.findChanges(QueryBuilder.anyDomainObject().build());
    }

    public Changes getHistoryByClass(Class clazz) {
        return javers.findChanges(QueryBuilder.byClass(clazz).build());
    }
}
