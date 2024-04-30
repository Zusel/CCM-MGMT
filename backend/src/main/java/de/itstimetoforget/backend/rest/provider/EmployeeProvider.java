package de.itstimetoforget.backend.rest.provider;

import de.itstimetoforget.backend.rest.entity.Employee;
import de.itstimetoforget.backend.rest.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeProvider {

    EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeProvider(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }
}
