package com.coffeshop.demo.controller;

import com.coffeshop.demo.model.Coffee;
import com.coffeshop.demo.repository.CoffeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/coffee")
public class CoffeeController {

    @Autowired
    private CoffeeRepository coffeeRepository;

    // ✅ Ambil semua data kopi
    @GetMapping
    public List<Coffee> getAllCoffee() {
        return coffeeRepository.findAll();
    }

    // ✅ Ambil data kopi berdasarkan kode
    @GetMapping("/{code}")
    public ResponseEntity<Coffee> getCoffeeByCode(@PathVariable String code) {
        Optional<Coffee> coffee = coffeeRepository.findById(code);
        return coffee.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Tambah data kopi baru
    @PostMapping
    public Coffee createCoffee(@RequestBody Coffee coffee) {
        return coffeeRepository.save(coffee);
    }

    // ✅ Update data kopi berdasarkan kode
    @PutMapping("/{code}")
    public ResponseEntity<Coffee> updateCoffee(@PathVariable String code, @RequestBody Coffee updatedCoffee) {
        return coffeeRepository.findById(code)
                .map(existing -> {
                    existing.setMerk(updatedCoffee.getMerk());
                    existing.setType(updatedCoffee.getType());
                    existing.setPrice(updatedCoffee.getPrice());
                    return ResponseEntity.ok(coffeeRepository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Hapus data kopi berdasarkan kode
    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteCoffee(@PathVariable String code) {
        return coffeeRepository.findById(code)
                .map(existing -> {
                    coffeeRepository.delete(existing);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
