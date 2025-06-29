package com.coffeshop.demo.controller;

import com.coffeshop.demo.model.Barista;
import com.coffeshop.demo.model.Coffee;
import com.coffeshop.demo.model.Sales;
import com.coffeshop.demo.repository.BaristaRepository;
import com.coffeshop.demo.repository.CoffeeRepository;
import com.coffeshop.demo.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080") 
@RestController
@RequestMapping("/sales")
public class SalesController {

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private CoffeeRepository coffeeRepository;

    @Autowired
    private BaristaRepository baristaRepository;

    // ✅ Get all sales
    @GetMapping
    public List<Sales> getAllSales() {
        return salesRepository.findAll();
    }

    // ✅ Get sales by ID
    @GetMapping("/{id}")
    public ResponseEntity<Sales> getSalesById(@PathVariable Long id) {
        return salesRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Create new sales (perbaikan)
    @PostMapping
    public ResponseEntity<?> createSales(@RequestBody Sales sales) {
        // Validasi input
        if (sales.getCoffee() == null || sales.getCoffee().getCode() == null ||
            sales.getBarista() == null || sales.getBarista().getId() == null) {
            return ResponseEntity.badRequest().body("Coffee code dan Barista id wajib diisi.");
        }

        // Ambil data dari database
        Coffee coffee = coffeeRepository.findById(sales.getCoffee().getCode()).orElse(null);
        Barista barista = baristaRepository.findById(sales.getBarista().getId()).orElse(null);

        if (coffee == null || barista == null) {
            return ResponseEntity.badRequest().body("Coffee atau Barista tidak ditemukan di database.");
        }

        // Simpan data sales
        Sales newSales = new Sales();
        newSales.setDate(sales.getDate());
        newSales.setCoffee(coffee);
        newSales.setBarista(barista);

        Sales savedSales = salesRepository.save(newSales);
        return ResponseEntity.ok(savedSales);
    }

    // ✅ Update sales
    @PutMapping("/{id}")
    public ResponseEntity<Sales> updateSales(@PathVariable Long id, @RequestBody Sales salesDetails) {
        return salesRepository.findById(id)
                .map(sales -> {
                    sales.setDate(salesDetails.getDate());

                    // Ambil ulang coffee dan barista dari database jika ID-nya valid
                    Coffee coffee = coffeeRepository.findById(salesDetails.getCoffee().getCode()).orElse(null);
                    Barista barista = baristaRepository.findById(salesDetails.getBarista().getId()).orElse(null);

                    if (coffee != null) {
                        sales.setCoffee(coffee);
                    }

                    if (barista != null) {
                        sales.setBarista(barista);
                    }

                    Sales updatedSales = salesRepository.save(sales);
                    return ResponseEntity.ok(updatedSales);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Delete sales
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSales(@PathVariable Long id) {
        return salesRepository.findById(id)
                .map(sales -> {
                    salesRepository.delete(sales);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Get sales by date
    @GetMapping("/date/{date}")
    public List<Sales> getSalesByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return salesRepository.findByDate(date);
    }

    // ✅ Get sales by coffee code
    @GetMapping("/coffee/{code}")
    public List<Sales> getSalesByCoffeeCode(@PathVariable String code) {
        return salesRepository.findByCoffee_Code(code);
    }

    // ✅ Get sales by barista ID
    @GetMapping("/barista/{id}")
    public List<Sales> getSalesByBaristaId(@PathVariable Long id) {
        return salesRepository.findByBarista_Id(id);
    }
}
