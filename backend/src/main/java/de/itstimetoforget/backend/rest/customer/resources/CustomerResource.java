package de.itstimetoforget.backend.rest.customer.resources;

import de.itstimetoforget.backend.rest.customer.entity.Customer;
import de.itstimetoforget.backend.rest.customer.service.CustomerService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"*"}, maxAge = 4800)
@RestController
@RequestMapping("/customer")
public class CustomerResource {

    CustomerService customerService;

    @Autowired
    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(path = "")
    public void createCustomer(@RequestBody Customer customer, HttpServletResponse response) {
        customerService.createCustomer(customer);
        response.setStatus(HttpServletResponse.SC_ACCEPTED);
    }

    @GetMapping(path = "")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }
}
