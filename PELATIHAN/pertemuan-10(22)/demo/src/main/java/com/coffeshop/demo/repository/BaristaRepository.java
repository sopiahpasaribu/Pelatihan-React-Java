package com.coffeshop.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.coffeshop.demo.model.Barista;

public interface BaristaRepository extends JpaRepository<Barista, Long> {

    List<Barista> findByGender(String gender);

    Optional<Barista> findByEmail(String email);
}
