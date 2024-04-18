package de.itstimetoforget.backend.rest.customer.repository;

import de.itstimetoforget.backend.rest.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
