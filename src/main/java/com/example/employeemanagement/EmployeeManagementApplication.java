package com.example.employeemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Application Class
 * 
 * @SpringBootApplication là annotation kết hợp của:
 * - @Configuration: Đánh dấu class này là nguồn cấu hình Bean
 * - @EnableAutoConfiguration: Tự động cấu hình Spring dựa trên dependencies
 * - @ComponentScan: Tự động scan và đăng ký các component trong package
 */
@SpringBootApplication
public class EmployeeManagementApplication {

    public static void main(String[] args) {
        // SpringApplication.run() khởi động ứng dụng Spring Boot
        // Auto-configuration sẽ tự động cấu hình embedded Tomcat server
        SpringApplication.run(EmployeeManagementApplication.class, args);
    }
}
