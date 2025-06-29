package com.pubcompany.demo.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pubcompany.demo.model.Employee;
import com.pubcompany.demo.repository.EmployeeRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;

    // Request untuk mendapatkan semua data karyawan
    // @GetMapping("")
    // public List<Employee> getAllEmployees() {
    // return employeeRepository.findAll();
    // }

    @GetMapping("/all")
    public Map<String, Object> findEmployeeList() {
        List<Employee> employees = employeeRepository.findAll();
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Berhasil mengambil semua data karyawan");
        response.put("data", employees);
        return response;
    }

    @PostMapping("/save")
    public Map<String, Object> saveEmployee(@RequestBody Employee employee) {
        Map<String, Object> response = new LinkedHashMap<>();

        // Cek apakah email sudah digunakan
        Optional<Employee> existing = employeeRepository.findByEmail(employee.getEmail());

        if (existing.isPresent()) {
            response.put("message", "Email sudah terdaftar, tidak bisa menambahkan data karyawan");
            return response;
        }

        // Jika email belum ada, simpan data
        Employee savedEmployee = employeeRepository.save(employee);
        response.put("message", "Berhasil menyimpan data karyawan");
        response.put("data", savedEmployee);
        return response;
    }

    @GetMapping("/employee-email/{email}")
    public Map<String, Object> findEmployeeByEmail(@PathVariable String email) {
        List<Employee> employees = employeeRepository.findEmployeeByEmail(email);
        Map<String, Object> response = new LinkedHashMap<>();
        if (employees.isEmpty()) {
            response.put("message", "Karyawan dengan email " + email + " tidak ditemukan");
        } else {
            response.put("message", "Data karyawan dengan email " + email + " berhasil ditemukan");
            response.put("data", employees);
        }
        return response;
    }

    @GetMapping("/employee-id/{id}")
    public Map<String, Object> findEmployeeById(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        Map<String, Object> response = new LinkedHashMap<>();
        if (employee == null) {
            response.put("message", "Karyawan dengan id " + id + " tidak ditemukan");
            response.put("data", null);
        } else {
            response.put("message", "Data karyawan dengan id " + id + " berhasil ditemukan");
            response.put("data", employee);
        }
        return response;
    }

    // @GetMapping("/{id}")
    // public Employee findEmployeeById(@PathVariable Long id) {
    // return employeeRepository.findById(id).orElse(null);
    // }

    @PutMapping("/update-by-email/{email}")
    public Map<String, Object> updateMahasiswa(@PathVariable String email, @RequestBody Employee newEmployee) {
        List<Employee> employees = employeeRepository.findEmployeeByEmail(email);
        Map<String, Object> response = new LinkedHashMap<>();
        if (employees == null) {
            response.put("message", "Karyawan dengan email " + email + " tidak ditemukan");
            response.put("data", null);
        } else {
            Employee exsiting = employees.get(0);
            exsiting.setName(newEmployee.getName());
            exsiting.setAge(newEmployee.getAge());
            Employee updatedEmployee = employeeRepository.save(exsiting);
            response.put("message", "Data karyawan dengan email " + email + " berhasil diperbarui");
            response.put("data", updatedEmployee);
        }
        return response;
    }

    @DeleteMapping("/delete-by-email/{email}")
    public Map<String, Object> deleteEmployee(@PathVariable String email) {
        List<Employee> employees = employeeRepository.findEmployeeByEmail(email);
        Map<String, Object> response = new LinkedHashMap<>();
        if (employees == null) {
            response.put("message", "Karyawan dengan email " + email + " tidak ditemukan");
            response.put("data", null);
        } else {
            Employee toDelete = employees.get(0);
            employeeRepository.delete(toDelete);
            response.put("message", "Data karyawan dengan email " + email + " berhasil dihapus");
            response.put("data", toDelete);
        }
        return response;
    }

}