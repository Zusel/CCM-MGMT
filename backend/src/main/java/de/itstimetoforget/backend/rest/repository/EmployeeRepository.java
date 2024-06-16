package de.itstimetoforget.backend.rest.repository;

import de.itstimetoforget.backend.rest.entity.Employee;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@JaversSpringDataAuditable
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
