DROP DATABASE IF EXISTS employeedb;
CREATE DATABASE employeedb;
USE employeedb;

DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS departments;

CREATE TABLE departments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255)
);

CREATE TABLE employees (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    department_id BIGINT,
    FOREIGN KEY (department_id) REFERENCES departments(id)
);

INSERT INTO departments (id, name, location) VALUES (1, 'IT', 'Building A');
INSERT INTO departments (id, name, location) VALUES (2, 'HR', 'Building B');
INSERT INTO departments (id, name, location) VALUES (3, 'Finance', 'Building C');

INSERT INTO employees (name, email, department_id) VALUES ('John Doe', 'john.doe@example.com', 1);
INSERT INTO employees (name, email, department_id) VALUES ('Jane Smith', 'jane.smith@example.com', 2);
INSERT INTO employees (name, email, department_id) VALUES ('Bob Johnson', 'bob.johnson@example.com', 1);
INSERT INTO employees (name, email, department_id) VALUES ('Alice Williams', 'alice.williams@example.com', 3);
