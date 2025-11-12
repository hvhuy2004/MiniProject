package com.example.employeemanagement.controller;

import com.example.employeemanagement.config.AppConfig;
import com.example.employeemanagement.service.EmployeeService;
import com.example.employeemanagement.service.UtilityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final EmployeeService employeeService;
    private final UtilityService utilityService;
    private final AppConfig.PasswordEncoder passwordEncoder;

    public HelloController(EmployeeService employeeService, 
                          UtilityService utilityService,
                          AppConfig.PasswordEncoder passwordEncoder) {
        this.employeeService = employeeService;
        this.utilityService = utilityService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Employee Management System - Spring Boot Module 2!";
    }

    @GetMapping("/generate-code")
    public String generateCode() {
        return employeeService.generateNewEmployeeCode();
    }

    @GetMapping("/format-name")
    public String formatName(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.formatEmployeeName(firstName, lastName);
    }

    @GetMapping("/create-employee")
    public String createEmployee(@RequestParam String firstName, 
                                 @RequestParam String lastName,
                                 @RequestParam String password) {
        return employeeService.createEmployee(firstName, lastName, password);
    }

    @GetMapping("/encode-password")
    public String encodePassword(@RequestParam String password) {
        return passwordEncoder.encode(password);
    }
}
