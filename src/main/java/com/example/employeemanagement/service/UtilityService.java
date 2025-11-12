package com.example.employeemanagement.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UtilityService {

    private final AtomicInteger counter = new AtomicInteger(1000);

    public String formatString(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        return input.trim().toUpperCase();
    }

    public String generateEmployeeCode() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        int sequence = counter.incrementAndGet();
        return "EMP-" + timestamp + "-" + String.format("%04d", sequence);
    }

    public String formatEmployeeName(String firstName, String lastName) {
        return formatString(firstName) + " " + formatString(lastName);
    }
}
