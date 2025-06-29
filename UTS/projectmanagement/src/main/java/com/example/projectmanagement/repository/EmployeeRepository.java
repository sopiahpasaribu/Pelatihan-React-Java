package com.example.projectmanagement.repository;

import com.example.projectmanagement.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByFullNameContainingIgnoreCase(String keyword);
    
    @Query("SELECT e FROM Employee e ORDER BY e.fullName ASC")
    List<Employee> findAllOrderByNameAsc();
    
    @Query("SELECT e FROM Employee e ORDER BY e.fullName DESC")
    List<Employee> findAllOrderByNameDesc();
}