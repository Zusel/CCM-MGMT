package de.itstimetoforget.backend.rest.customer.service;

import de.itstimetoforget.backend.rest.customer.entity.Customer;
import de.itstimetoforget.backend.rest.customer.processor.CustomerProcessor;
import de.itstimetoforget.backend.rest.customer.provider.CustomerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    CustomerProcessor customerProcessor;
    CustomerProvider customerProvider;

    @Autowired
    public CustomerService(CustomerProcessor customerProcessor, CustomerProvider customerProvider) {
        this.customerProcessor = customerProcessor;
        this.customerProvider = customerProvider;
    }

    public void createCustomer(Customer customer) {
        customerProcessor.createCustomer(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerProvider.getAllCustomers();
    }
}
