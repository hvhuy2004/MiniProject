# Module 5: Validation & Exception Handling - Testing Guide

## Các tính năng đã triển khai

### 1. Bean Validation
- `@NotBlank`: Tên và email không được rỗng
- `@Size`: Tên phải từ 2-100 ký tự
- `@Email`: Email phải hợp lệ

### 2. Exception Handling
- `ResourceNotFoundException`: Xử lý khi không tìm thấy Employee (404)
- `MethodArgumentNotValidException`: Xử lý validation errors (400)
- `GlobalExceptionHandler`: Xử lý tất cả exceptions

## Test Cases

### 1. Test Validation - Email không hợp lệ
```bash
POST http://localhost:8080/api/employees
Content-Type: application/json

{
  "name": "John Doe",
  "email": "invalid-email"
}
```

**Kết quả mong đợi**: 400 Bad Request với thông báo lỗi chi tiết

### 2. Test Validation - Name rỗng
```bash
POST http://localhost:8080/api/employees
Content-Type: application/json

{
  "name": "",
  "email": "john@example.com"
}
```

**Kết quả mong đợi**: 400 Bad Request với message "Name is required and cannot be empty"

### 3. Test Validation - Name quá ngắn
```bash
POST http://localhost:8080/api/employees
Content-Type: application/json

{
  "name": "J",
  "email": "john@example.com"
}
```

**Kết quả mong đợi**: 400 Bad Request với message "Name must be between 2 and 100 characters"

### 4. Test ResourceNotFoundException
```bash
GET http://localhost:8080/api/employees/999
```

**Kết quả mong đợi**: 404 Not Found với message "Employee not found with id: '999'"

### 5. Test Valid Employee Creation
```bash
POST http://localhost:8080/api/employees
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john.doe@example.com"
}
```

**Kết quả mong đợi**: 201 Created với employee data

### 6. Test Update với ID không tồn tại
```bash
PUT http://localhost:8080/api/employees/999
Content-Type: application/json

{
  "name": "Updated Name",
  "email": "updated@example.com"
}
```

**Kết quả mong đợi**: 404 Not Found

### 7. Test Delete với ID không tồn tại
```bash
DELETE http://localhost:8080/api/employees/999
```

**Kết quả mong đợi**: 404 Not Found

## Cấu trúc Error Response

Tất cả errors đều trả về format:

```json
{
  "timestamp": "2025-11-13T10:30:45.123",
  "status": 400,
  "error": "Validation Failed",
  "message": "Invalid input data. Please check the details.",
  "path": "/api/employees",
  "details": [
    "email: Email should be valid",
    "name: Name is required and cannot be empty"
  ]
}
```

## Chạy ứng dụng

1. Build project:
```bash
mvn clean install
```

2. Run application:
```bash
mvn spring-boot:run
```

3. Hoặc chạy trực tiếp từ IntelliJ IDEA:
   - Mở `EmployeeManagementApplication.java`
   - Click vào nút Run (▶) hoặc nhấn Shift+F10

## Test với Postman hoặc cURL

### Postman
1. Mở Postman
2. Tạo requests theo các test cases ở trên
3. Kiểm tra response status và body

### cURL
```bash
# Test validation error
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{"name":"","email":"invalid-email"}'

# Test resource not found
curl -X GET http://localhost:8080/api/employees/999

# Test valid creation
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{"name":"John Doe","email":"john@example.com"}'
```
