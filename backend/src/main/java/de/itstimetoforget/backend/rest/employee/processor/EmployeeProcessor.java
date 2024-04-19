package de.itstimetoforget.backend.rest.employee.processor;

import de.itstimetoforget.backend.rest.employee.entity.Employee;
import de.itstimetoforget.backend.rest.employee.repository.EmployeeRepository;
import de.itstimetoforget.backend.rest.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeProcessor {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeProcessor(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void createEmployees(List<Employee> employees) {
        List<Employee> finishedEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                byte[] digest = messageDigest.digest(employee.getPassword().getBytes());
                String hashedPassword = bytesToHex(digest);
                employee.setPassword(hashedPassword);
                finishedEmployees.add(employee);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
        employeeRepository.saveAll(finishedEmployees);
    }

    public void createEmployee(Employee employee) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] digest = messageDigest.digest(employee.getPassword().getBytes());
            String hashedPassword = bytesToHex(digest);
            employee.setPassword(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        employeeRepository.save(employee);
    }

    private String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void deleteEmployee(Employee employee) {
        employeeRepository.delete(employee);
    }


}

