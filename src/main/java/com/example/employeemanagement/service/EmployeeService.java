package com.example.employeemanagement.service;

import com.example.employeemanagement.config.AppConfig;
import com.example.employeemanagement.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final UtilityService utilityService;
    private final AppConfig.PasswordEncoder passwordEncoder;
    private final List<Employee> employees = new ArrayList<>();

    public EmployeeService(UtilityService utilityService, AppConfig.PasswordEncoder passwordEncoder) {
        this.utilityService = utilityService;
        this.passwordEncoder = passwordEncoder;
        initializeData();
    }

    private void initializeData() {
        employees.add(new Employee("EMP-001", "John", "Doe", "john.doe@example.com", "Developer", 50000.0));
        employees.add(new Employee("EMP-002", "Jane", "Smith", "jane.smith@example.com", "Manager", 70000.0));
        employees.add(new Employee("EMP-003", "Bob", "Johnson", "bob.johnson@example.com", "Designer", 45000.0));
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees);
    }

    public Optional<Employee> getEmployeeById(String id) {
        return employees.stream()
                .filter(emp -> emp.getId().equals(id))
                .findFirst();
    }

    public Employee createEmployee(Employee employee) {
        String employeeCode = utilityService.generateEmployeeCode();
        employee.setId(employeeCode);
        
        String formattedFirstName = utilityService.formatString(employee.getFirstName());
        String formattedLastName = utilityService.formatString(employee.getLastName());
        employee.setFirstName(formattedFirstName);
        employee.setLastName(formattedLastName);
        
        employees.add(employee);
        return employee;
    }

    public String createEmployee(String firstName, String lastName, String password) {
        String employeeCode = utilityService.generateEmployeeCode();
        String fullName = utilityService.formatEmployeeName(firstName, lastName);
        String encodedPassword = passwordEncoder.encode(password);

        return String.format("Employee Created - Code: %s, Name: %s, Password Hash: %s",
                employeeCode, fullName, encodedPassword);
    }

    public String formatEmployeeName(String firstName, String lastName) {
        return utilityService.formatEmployeeName(firstName, lastName);
    }

    public String generateNewEmployeeCode() {
        return utilityService.generateEmployeeCode();
    }
}
