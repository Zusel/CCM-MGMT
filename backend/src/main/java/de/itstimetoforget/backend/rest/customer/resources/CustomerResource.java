package de.itstimetoforget.backend.rest.customer.resources;

import de.itstimetoforget.backend.rest.customer.entity.Customer;
import de.itstimetoforget.backend.rest.customer.service.CustomerService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public Customer createCustomer(@RequestBody Customer customer, HttpServletResponse response) {
        return customerService.createCustomer(customer);
    }

    @GetMapping(path = "")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @DeleteMapping(path = "")
    public void deleteCustomerById(@RequestBody Customer customer, HttpServletResponse response) {
        if (customer.getId() != null && customerService.getCustomerById(customer.getId()) != null) {
            customerService.deleteCustomerById(customer.getId());
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @PostMapping("/update")
    public void updateCustomer(@RequestBody Customer customer, HttpServletResponse response) {
        if (customer.getId() != null && customerService.getCustomerById(customer.getId()) != null) {
            customerService.updateCustomer(customer);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @PostMapping(path = "/generate")
    public void generateCustomer(@RequestBody String count, HttpServletResponse response) {
        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < Integer.parseInt(count); i++) {
            Customer customer = new Customer();
            customer.setFirstName(RandomStringUtils.randomAlphabetic(5));
            customer.setLastName(RandomStringUtils.randomAlphabetic(5));
            customer.setEmail(RandomStringUtils.randomAlphabetic(5));
            customer.setCity(RandomStringUtils.randomAlphabetic(5));
            customer.setPostcode(RandomStringUtils.randomNumeric(5, 10));
            customer.setStreet(RandomStringUtils.randomAlphabetic(5));
            customer.setStreetNumber(RandomStringUtils.randomNumeric(5, 10));
            customer.setMobileNumber(RandomStringUtils.randomNumeric(5, 10));
            customer.setLandlineNumber(RandomStringUtils.randomNumeric(5, 10));
            customers.add(customer);
        }
        customerService.createCustomers(customers);
    }


}
