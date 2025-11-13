package com.example.employeemanagement.controller;

import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.service.DepartmentService;
import com.example.employeemanagement.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeWebController {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    public EmployeeWebController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @GetMapping("/list")
    public String listEmployees(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        model.addAttribute("pageTitle", "Employee List");
        return "employees/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departmentService.getAllDepartments());
        model.addAttribute("pageTitle", "Add New Employee");
        return "employees/add";
    }

    @PostMapping("/add")
    public String addEmployee(@Valid @ModelAttribute("employee") Employee employee,
                              BindingResult bindingResult,
                              Model model,
                              RedirectAttributes redirectAttributes) {
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("departments", departmentService.getAllDepartments());
            model.addAttribute("pageTitle", "Add New Employee");
            return "employees/add";
        }
        
        employeeService.createEmployee(employee);
        redirectAttributes.addFlashAttribute("successMessage", 
            "Employee added successfully!");
        return "redirect:/employees/list";
    }

    @GetMapping("/search")
    public String showSearchForm(Model model) {
        model.addAttribute("pageTitle", "Search Employees");
        return "employees/search";
    }

    @GetMapping("/search/results")
    public String searchEmployees(@RequestParam(required = false) String name,
                                   @RequestParam(required = false) String department,
                                   Model model) {
        List<Employee> results;
        String searchInfo = "";
        
        if (name != null && !name.trim().isEmpty()) {
            results = employeeService.searchByName(name);
            searchInfo = "Search results for name: \"" + name + "\"";
        } else if (department != null && !department.trim().isEmpty()) {
            results = employeeService.findByDepartment(department);
            searchInfo = "Search results for department: \"" + department + "\"";
        } else {
            results = employeeService.getAllEmployees();
            searchInfo = "All employees";
        }
        
        model.addAttribute("employees", results);
        model.addAttribute("searchInfo", searchInfo);
        model.addAttribute("resultCount", results.size());
        model.addAttribute("pageTitle", "Search Results");
        return "employees/search";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("departments", departmentService.getAllDepartments());
        model.addAttribute("pageTitle", "Edit Employee");
        return "employees/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateEmployee(@PathVariable Long id,
                                 @Valid @ModelAttribute("employee") Employee employee,
                                 BindingResult bindingResult,
                                 Model model,
                                 RedirectAttributes redirectAttributes) {
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("departments", departmentService.getAllDepartments());
            model.addAttribute("pageTitle", "Edit Employee");
            return "employees/edit";
        }
        
        employeeService.updateEmployee(id, employee);
        redirectAttributes.addFlashAttribute("successMessage", 
            "Employee updated successfully!");
        return "redirect:/employees/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        employeeService.deleteEmployee(id);
        redirectAttributes.addFlashAttribute("successMessage", 
            "Employee deleted successfully!");
        return "redirect:/employees/list";
    }
}
