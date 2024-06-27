package de.itstimetoforget.backend.rest.service;

import de.itstimetoforget.backend.rest.entity.Employee;
import de.itstimetoforget.backend.rest.processor.EmployeeProcessor;
import de.itstimetoforget.backend.rest.provider.EmployeeProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeProvider employeeProvider;

    private final EmployeeProcessor employeeProcessor;

    @Autowired
    public EmployeeService(EmployeeProvider employeeProvider, EmployeeProcessor employeeProcessor) {
        this.employeeProvider = employeeProvider;
        this.employeeProcessor = employeeProcessor;
    }

    public List<Employee> getAllEmployees() {
        return employeeProvider.getAllEmployees();
    }

    public void createEmployees(List<Employee> employees) {
        employeeProcessor.createEmployees(employees);
    }

    public void createEmployee(Employee employee) {
        employeeProcessor.createEmployee(employee);
    }

    public Employee getEmployeeById(Long id) {
        return employeeProvider.getEmployeeById(id);
    }

    public void updateEmployee(Employee employee) {
        employeeProcessor.updateEmployee(employee);
    }

    public void deleteEmployee(Employee employee) {
        employeeProcessor.deleteEmployee(employee);
    }

    public Optional<Employee> getEmployeeByShortName(String shortName) {
        return employeeProvider.getEmployeeByShortName(shortName);
    }

    public Long getIdByShortName(String shortName){
        return employeeProvider.getIdByShortName(shortName);
    }
}
