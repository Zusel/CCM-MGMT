package de.itstimetoforget.backend.rest.resource;

import de.itstimetoforget.backend.rest.entity.Customer;
import de.itstimetoforget.backend.rest.entity.Employee;
import de.itstimetoforget.backend.rest.entity.Order;
import de.itstimetoforget.backend.rest.service.HistoryService;
import jakarta.websocket.server.PathParam;
import javassist.NotFoundException;
import org.javers.core.Changes;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 4800)
@RequestMapping(path = "/history")
public class HistoryResource {


    private HistoryService historyService;

    public HistoryResource(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping(path = "/customer/{id}")
    public Changes getCustomerHistory(@PathVariable Long id) {
        return historyService.getCustomerHistory(id);
    }

    @GetMapping(path = "")
    public Changes getCompleteHistory() {
        return historyService.getCompleteHistory();
    }

    @GetMapping("/{type}")
    public Changes getHistoryByType(@PathVariable String type) throws NotFoundException {
        return switch (type) {
            case "customer" -> historyService.getChangesByClass(Customer.class);
            case "employee" -> historyService.getChangesByClass(Employee.class);
            case "order" -> historyService.getChangesByClass(Order.class);
            default -> throw new NotFoundException("Unknown type!");
        };
    }
}
