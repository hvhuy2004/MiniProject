package com.example.employeemanagement.service;

import com.example.employeemanagement.config.AppConfig;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final UtilityService utilityService;
    private final AppConfig.PasswordEncoder passwordEncoder;

    public EmployeeService(UtilityService utilityService, AppConfig.PasswordEncoder passwordEncoder) {
        this.utilityService = utilityService;
        this.passwordEncoder = passwordEncoder;
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
