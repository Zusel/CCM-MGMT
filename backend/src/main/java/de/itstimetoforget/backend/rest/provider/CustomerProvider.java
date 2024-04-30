package de.itstimetoforget.backend.rest.provider;

import de.itstimetoforget.backend.rest.entity.Customer;
import de.itstimetoforget.backend.rest.repository.CustomerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class CustomerProvider {

    @PersistenceContext
    EntityManager entityManager;

    CustomerRepository customerRepository;

    @Autowired
    public CustomerProvider(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public List<Customer> filterCustomer(Customer customer) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
        Root<Customer> customerRoot = criteriaQuery.from(Customer.class);
        criteriaQuery.select(customerRoot);
        List<Predicate> predicates = new ArrayList<>();
        if (!StringUtils.isBlank(customer.getFirstName())) {
            predicates.add(criteriaBuilder.like(customerRoot.get("firstName"), "%" + customer.getFirstName() + "%"));
        }
        if (!StringUtils.isBlank(customer.getLastName())) {
            predicates.add(criteriaBuilder.like(customerRoot.get("lastName"), "%" + customer.getLastName() + "%"));
        }
        if (!StringUtils.isBlank(customer.getCity())) {
            predicates.add(criteriaBuilder.like(customerRoot.get("city"), "%" + customer.getCity() + "%"));
        }
        if (!StringUtils.isBlank(customer.getEmail())) {
            predicates.add(criteriaBuilder.like(customerRoot.get("email"), "%" + customer.getEmail() + "%"));
        }
        if (!StringUtils.isBlank(customer.getLandlineNumber())) {
            predicates.add(criteriaBuilder.like(customerRoot.get("landlineNumber"), "%" + customer.getLandlineNumber() + "%"));
        }
        if (!StringUtils.isBlank(customer.getStreet())) {
            predicates.add(criteriaBuilder.like(customerRoot.get("street"), "%" + customer.getStreet() + "%"));
        }
        if (!StringUtils.isBlank(customer.getPostcode())) {
            predicates.add(criteriaBuilder.like(customerRoot.get("postcode"), "%" + customer.getPostcode() + "%"));
        }
        if (!StringUtils.isBlank(customer.getStreetNumber())) {
            predicates.add(criteriaBuilder.like(customerRoot.get("streetNumber"), "%" + customer.getStreetNumber() + "%"));
        }
        if (!StringUtils.isBlank(customer.getMobileNumber())) {
            predicates.add(criteriaBuilder.like(customerRoot.get("mobileNumber"), "%" + customer.getMobileNumber() + "%"));
        }


        if (!predicates.isEmpty()) {
            criteriaQuery.where(predicates.toArray(new Predicate[0]));
        }
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
