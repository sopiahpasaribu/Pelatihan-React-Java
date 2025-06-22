package com.pubcompany.demo.controller;

import com.pubcompany.demo.model.Employee;
import com.pubcompany.demo.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    // ✅ GET: Ambil semua karyawan
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Berhasil mengambil data karyawan");
        response.put("data", employees);
        return ResponseEntity.ok(response);
    }

    // ✅ GET: Ambil 1 karyawan berdasarkan ID
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employeeOpt = employeeRepository.findById(id);
        Map<String, Object> response = new LinkedHashMap<>();

        if (employeeOpt.isPresent()) {
            response.put("message", "Data karyawan ditemukan");
            response.put("data", employeeOpt.get());
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Data karyawan tidak ditemukan");
            return ResponseEntity.status(404).body(response);
        }
    }

    // ✅ POST: Tambah karyawan baru
    @PostMapping
    public ResponseEntity<Map<String, Object>> tambahKaryawan(@RequestBody Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        Map<String, Object> response = new LinkedHashMap<>();
        // response.put("message", "Berhasil menyimpan data karyawan");
        response.put("data", savedEmployee);
        return ResponseEntity.ok(response);
    }

    // ✅ PUT: Update karyawan berdasarkan ID
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateKaryawan(@PathVariable Long id, @RequestBody Employee newEmployeeData) {
        Optional<Employee> employeeOpt = employeeRepository.findById(id);
        Map<String, Object> response = new LinkedHashMap<>();

        if (employeeOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            employee.setName(newEmployeeData.getName());
            employee.setEmail(newEmployeeData.getEmail());
            employee.setAge(newEmployeeData.getAge());

            Employee updated = employeeRepository.save(employee);

            response.put("message", "Berhasil memperbarui data karyawan");
            response.put("data", updated);
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Data karyawan tidak ditemukan");
            return ResponseEntity.status(404).body(response);
        }
    }

    // ✅ DELETE: Hapus karyawan berdasarkan ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteKaryawan(@PathVariable Long id) {
        Optional<Employee> employeeOpt = employeeRepository.findById(id);
        Map<String, Object> response = new LinkedHashMap<>();

        if (employeeOpt.isPresent()) {
            employeeRepository.deleteById(id);
            response.put("message", "Berhasil menghapus data karyawan");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Data karyawan tidak ditemukan");
            return ResponseEntity.status(404).body(response);
        }
    }
}
