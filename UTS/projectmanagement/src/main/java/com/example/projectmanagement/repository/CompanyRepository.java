package com.example.projectmanagement.repository;

import com.example.projectmanagement.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    // Search by name
    List<Company> findByNameContainingIgnoreCase(String name);

    // Sort by name asc
    @Query("SELECT c FROM Company c ORDER BY c.name ASC")
    List<Company> findAllOrderByNameAsc();

    // Sort by name desc
    @Query("SELECT c FROM Company c ORDER BY c.name DESC")
    List<Company> findAllOrderByNameDesc();
}
