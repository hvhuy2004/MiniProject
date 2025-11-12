INSERT INTO departments (id, name, location) VALUES (1, 'IT', 'Building A');
INSERT INTO departments (id, name, location) VALUES (2, 'HR', 'Building B');
INSERT INTO departments (id, name, location) VALUES (3, 'Finance', 'Building C');

INSERT INTO employees (first_name, last_name, email, position, salary, department_id) 
VALUES ('John', 'Doe', 'john.doe@example.com', 'Developer', 50000, 1);

INSERT INTO employees (first_name, last_name, email, position, salary, department_id) 
VALUES ('Jane', 'Smith', 'jane.smith@example.com', 'Manager', 70000, 2);

INSERT INTO employees (first_name, last_name, email, position, salary, department_id) 
VALUES ('Bob', 'Johnson', 'bob.johnson@example.com', 'Designer', 45000, 1);

INSERT INTO employees (first_name, last_name, email, position, salary, department_id) 
VALUES ('Alice', 'Williams', 'alice.williams@example.com', 'Analyst', 55000, 3);
