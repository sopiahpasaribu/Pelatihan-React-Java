package com.example.projectmanagement.controller;

import com.example.projectmanagement.model.Department;
import com.example.projectmanagement.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;

    // Ambil semua departemen
@GetMapping
public ResponseEntity<?> getAllDepartments() {
    try {
        List<Department> departments = departmentRepository.findAll();
        
        if (departments.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK)
                   .body("No departments found in the database.");
        }
        
        return ResponseEntity.ok(departments);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
               .body("Failed to retrieve departments: " + e.getMessage());
    }
}
    // Sorting ASC/DESC berdasarkan nama
    @GetMapping("/sort")
    public ResponseEntity<List<Department>> sortDepartments(
            @RequestParam(defaultValue = "asc") String direction) {
        List<Department> departments = "desc".equalsIgnoreCase(direction)
                ? departmentRepository.findAllOrderByNameDesc()
                : departmentRepository.findAllOrderByNameAsc();

        return ResponseEntity.ok(departments);
    }

    // Ambil 1 departemen berdasarkan ID
    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        return departmentRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Tambah departemen baru, dicegah duplikat nama
    @PostMapping
    public ResponseEntity<?> createDepartment(@RequestBody Department department) {
        Optional<Department> existing = departmentRepository.findByName(department.getName());
        if (existing.isPresent()) {
            return ResponseEntity.badRequest().body("Department with this name already exists.");
        }
        Department saved = departmentRepository.save(department);
        return ResponseEntity.ok(saved);
    }

    // Update data departemen
    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id,
                                                       @RequestBody Department departmentDetails) {
        return departmentRepository.findById(id)
                .map(department -> {
                    department.setName(departmentDetails.getName());
                    department.setCompany(departmentDetails.getCompany());
                    return ResponseEntity.ok(departmentRepository.save(department));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Hapus departemen
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable Long id) {
        return departmentRepository.findById(id)
                .map(department -> {
                    departmentRepository.delete(department);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
