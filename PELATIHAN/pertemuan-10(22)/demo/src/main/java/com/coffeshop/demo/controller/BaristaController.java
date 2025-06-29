package com.coffeshop.demo.controller;

import com.coffeshop.demo.model.Barista;
import com.coffeshop.demo.repository.BaristaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/barista")
public class BaristaController {

    @Autowired
    private BaristaRepository baristaRepository;

    // ✅ Get All Baristas
    @GetMapping
    public List<Barista> getAllBaristas() {
        return baristaRepository.findAll();
    }

    // ✅ Get Barista by ID
    @GetMapping("/{id}")
    public ResponseEntity<Barista> getBaristaById(@PathVariable Long id) {
        return baristaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Create New Barista
    @PostMapping
    public Barista createBarista(@RequestBody Barista barista) {
        return baristaRepository.save(barista);
    }

    // ✅ Update Barista
    @PutMapping("/{id}")
    public ResponseEntity<Barista> updateBarista(@PathVariable Long id, @RequestBody Barista updatedBarista) {
        return baristaRepository.findById(id)
                .map(barista -> {
                    barista.setName(updatedBarista.getName());
                    barista.setAge(updatedBarista.getAge());
                    barista.setGender(updatedBarista.getGender());
                    barista.setEmail(updatedBarista.getEmail());
                    return ResponseEntity.ok(baristaRepository.save(barista));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Delete Barista
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBarista(@PathVariable Long id) {
        return baristaRepository.findById(id)
                .map(barista -> {
                    baristaRepository.delete(barista);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
