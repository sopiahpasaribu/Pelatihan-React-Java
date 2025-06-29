package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/search/{name}")
    public List<Employee> searchByName(@PathVariable String name) {
        return employeeRepository.findByNameContainingIgnoreCase(name);
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeRepository.findById(id).orElseThrow(() ->
            new RuntimeException("Employee not found with id: " + id));
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() ->
            new RuntimeException("Employee not found with id: " + id));
        
        employee.setName(employeeDetails.getName());
        return employeeRepository.save(employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
    }

    // ✅ Search by query parameter
    @GetMapping("/search")
    public List<Employee> searchEmployeesByName(@RequestParam String name) {
        return employeeRepository.findByNameContainingIgnoreCase(name);
    }

    // ✅ Get employees sorted descending
    @GetMapping("/sort/desc")
    public List<Employee> getEmployeesSortedDesc() {
        return employeeRepository.findAllByOrderByNameDesc();
    }

    // ✅ Get employees sorted ascending
    @GetMapping("/sort/asc")
    public List<Employee> getEmployeesSortedAsc() {
        return employeeRepository.findAllByOrderByNameAsc();
    }
}
