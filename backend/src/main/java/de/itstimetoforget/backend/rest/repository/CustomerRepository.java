package de.itstimetoforget.backend.rest.repository;

import de.itstimetoforget.backend.rest.entity.Customer;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@JaversSpringDataAuditable
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
