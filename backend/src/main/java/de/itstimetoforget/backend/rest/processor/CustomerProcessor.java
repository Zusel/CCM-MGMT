package de.itstimetoforget.backend.rest.processor;

import de.itstimetoforget.backend.rest.entity.Customer;
import de.itstimetoforget.backend.rest.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerProcessor {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerProcessor(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }

    public void createCustomers(List<Customer> customers) {
        customerRepository.saveAll(customers);
    }

    public void updateCustomerById(Customer customer) {
        customerRepository.save(customer);
    }

}
