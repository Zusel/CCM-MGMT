package de.itstimetoforget.backend.rest.employee.resources;


import de.itstimetoforget.backend.rest.customer.entity.Customer;
import de.itstimetoforget.backend.rest.employee.entity.Employee;
import de.itstimetoforget.backend.rest.employee.service.EmployeeService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = {"*"}, maxAge = 4800)
@RestController
@RequestMapping("/employee")
public class EmployeeResource {

    EmployeeService employeeService;

    @Autowired
    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping(value = "")
    public void createEmployee(@RequestBody Employee employee) {
        employeeService.createEmployee(employee);
    }

    @DeleteMapping(value = "")
    public void deleteEmployeeById(@RequestBody Employee employee, HttpServletResponse response) {
        if (employee.getId() != null && employeeService.getEmployeeById(employee.getId()) != null) {
            employeeService.deleteEmployee(employee);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @PostMapping(value = "/update")
    public void updateEmployee(@RequestBody Employee employee, HttpServletResponse response) {
        if (employee.getId() != null && employeeService.getEmployeeById(employee.getId()) != null) {
            employeeService.updateEmployee(employee);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @PostMapping(value = "/generate")
    public void generateEmployee(@RequestBody String count) {
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < Integer.parseInt(count); i++) {
            Employee employee = new Employee();
            employee.setFirstName(RandomStringUtils.randomAlphabetic(5));
            employee.setLastName(RandomStringUtils.randomAlphabetic(5));
            employee.setEmail(RandomStringUtils.randomAlphabetic(5));
            employee.setPassword(RandomStringUtils.randomAlphabetic(5));
            employee.setShortName(RandomStringUtils.randomAlphabetic(5));
            employee.setActive(true);
            employees.add(employee);
        }
        employeeService.createEmployees(employees);
    }
}
