package com.example.mahasiswa;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mahasiswa")
public class MahasiswaController {
    private final MahasiswaService mahasiswaService;

    public MahasiswaController(MahasiswaService mahasiswaService) {
        this.mahasiswaService = mahasiswaService;
    }

    @GetMapping
    public List<Mahasiswa> semuaMahasiswa() {
        return mahasiswaService.getAllMahasiswa();
    }

    @PostMapping
    public Mahasiswa tambahMahasiswa(@RequestBody Mahasiswa mahasiswa) {
        return mahasiswaService.simpanMahasiswa(mahasiswa);
    }

    @GetMapping("/{id}")
    public Mahasiswa getMahasiswa(@PathVariable String id) {
        Optional<Mahasiswa> mahasiswa = mahasiswaService.getMahasiswaById(id);
        return mahasiswa.orElse(null);
    }

    @PutMapping("/{id}")
    public Mahasiswa updateMahasiswa(@PathVariable String id, @RequestBody Mahasiswa mahasiswa) {
        return mahasiswaService.updateMahasiswa(id, mahasiswa);
    }

    @DeleteMapping("/{id}")
    public boolean hapusMahasiswa(@PathVariable String id) {
        return mahasiswaService.hapusMahasiswa(id);
    }
}