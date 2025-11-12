package com.example.employeemanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello Controller - REST API Controller
 * 
 * @RestController kết hợp @Controller và @ResponseBody
 * - Tự động serialize return value thành JSON
 * - Đánh dấu class này là REST controller
 */
@RestController
public class HelloController {

    /**
     * Hello World Endpoint
     * 
     * @GetMapping: Xử lý HTTP GET request tới /hello
     * @return String message
     */
    @GetMapping("/hello")
    public String hello() {
        return "Hello from Employee Management System - Spring Boot Module 1!";
    }
}
