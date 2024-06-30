package de.itstimetoforget.backend.rest.provider;

import de.itstimetoforget.backend.rest.entity.Employee;
import de.itstimetoforget.backend.rest.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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

    @Cacheable(value = "employee",key = "#id")
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Optional<Employee> getEmployeeByShortName(String shortName) {
        return employeeRepository.getEmployeeByShortName(shortName);
    }

    public Long getIdByShortName(String shortName) {
        return employeeRepository.getIdByShortName(shortName);
    }

}
