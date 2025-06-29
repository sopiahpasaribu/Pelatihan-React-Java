package com.coffeshop.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.coffeshop.demo.model.Coffee;

public interface CoffeeRepository extends JpaRepository<Coffee, String> {

    // Cari berdasarkan merk
    List<Coffee> findByMerk(String merk);

    // Cari berdasarkan kode (opsional karena kode adalah primary key)
    Optional<Coffee> findByCode(String code);
}
