package de.itstimetoforget.backend.rest.customer.provider;

import de.itstimetoforget.backend.rest.customer.entity.Customer;
import de.itstimetoforget.backend.rest.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerProvider {

    CustomerRepository customerRepository;

    @Autowired
    public CustomerProvider(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}
