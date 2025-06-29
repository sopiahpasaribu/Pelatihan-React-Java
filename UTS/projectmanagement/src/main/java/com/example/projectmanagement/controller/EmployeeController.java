package com.example.projectmanagement.controller;

import com.example.projectmanagement.model.Employee;
import com.example.projectmanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/all")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/by-id/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @PostMapping("/create")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @PutMapping("/update/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null) {
            employee.setFullName(employeeDetails.getFullName());
            employee.setRole(employeeDetails.getRole());
            employee.setDepartment(employeeDetails.getDepartment());
            return employeeRepository.save(employee);
        }
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
    }

    @GetMapping("/search/{keyword}")
    public List<Employee> searchEmployees(@PathVariable String keyword) {
        return employeeRepository.findByFullNameContainingIgnoreCase(keyword);
    }

    @GetMapping("/sort/asc")
    public List<Employee> sortEmployeesAsc() {
        return employeeRepository.findAllOrderByNameAsc();
    }

    @GetMapping("/sort/desc")
    public List<Employee> sortEmployeesDesc() {
        return employeeRepository.findAllOrderByNameDesc();
    }
}