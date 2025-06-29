package com.pubcompany.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pubcompany.demo.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Add custom queries if needed
    List<Employee> findEmployeeByEmail(String email);

    Optional<Employee> findByEmail(String email);
}