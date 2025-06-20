package com.example.buku;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/buku")
public class BukuController {
    private final BukuService bukuService;

    public BukuController(BukuService bukuService) {
        this.bukuService = bukuService;
    }

    @GetMapping
    public List<Buku> getAllBuku() {
        return bukuService.getAllBuku();
    }

    @PostMapping
    public Buku tambahBuku(@RequestBody Buku buku) {
        return bukuService.tambahBuku(buku);
    }

    @GetMapping("/{id}")
    public Buku getBukuById(@PathVariable String id) {
        Optional<Buku> buku = bukuService.getBukuById(id);
        return buku.orElse(null);
    }

    @PutMapping("/{id}")
    public Buku updateBuku(@PathVariable String id, @RequestBody Buku bukuUpdate) {
        return bukuService.updateBuku(id, bukuUpdate);
    }

    @DeleteMapping("/{id}")
    public boolean hapusBuku(@PathVariable String id) {
        return bukuService.hapusBuku(id);
    }
}