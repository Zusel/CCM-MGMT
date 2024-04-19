package de.itstimetoforget.backend.rest.employee.repository;

import de.itstimetoforget.backend.rest.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
