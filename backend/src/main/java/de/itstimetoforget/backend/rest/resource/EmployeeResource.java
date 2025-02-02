package de.itstimetoforget.backend.rest.resource;


import de.itstimetoforget.backend.rest.entity.Employee;
import de.itstimetoforget.backend.rest.service.EmployeeService;
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

    @GetMapping("/shortnames")
    public List<String> getShortNames() {
        List<Employee> employees = employeeService.getAllEmployees();
        return employees.stream().map(Employee::getShortName).toList();
    }

    @GetMapping("/getIdForShortName")
    public Long getEmployeeIdByShortName(@RequestParam String shortName, HttpServletResponse response) {
        List<Employee> employees = employeeService.getAllEmployees();
        Long employeeId = employees.stream().filter(e -> e.getShortName().equals(shortName)).map(Employee::getId).findFirst().orElse(null);
        if (employeeId == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return employeeId;
    }

    @PostMapping("/validate")
    public boolean validateEmployee(@RequestBody Employee employee) {
        if (employee.getId() != null && employee.getShortName() != null) {
            Employee filteredEmployee = employeeService.getEmployeeById(employee.getId());
            return filteredEmployee.getShortName().equals(employee.getShortName());
        }
        return false;
    }
}
