package com.coffeshop.demo.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.coffeshop.demo.model.Sales;

public interface SalesRepository extends JpaRepository<Sales, Long> {
    List<Sales> findByDate(LocalDate date);
    List<Sales> findByCoffee_Code(String code);  // Changed to match new property name
    List<Sales> findByBarista_Id(Long id);       // Changed to match new property name
}