package com.example.projectmanagement.repository;

import com.example.projectmanagement.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    // Cek apakah ada nama departemen yang sama
    Optional<Department> findByName(String name);

    // Sorting ASC berdasarkan nama
    @Query("SELECT d FROM Department d ORDER BY d.name ASC")
    List<Department> findAllOrderByNameAsc();

    // Sorting DESC berdasarkan nama
    @Query("SELECT d FROM Department d ORDER BY d.name DESC")
    List<Department> findAllOrderByNameDesc();
}
