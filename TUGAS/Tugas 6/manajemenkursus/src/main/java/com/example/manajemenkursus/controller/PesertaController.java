package com.example.manajemenkursus.controller;

import com.example.manajemenkursus.model.*;
import com.example.manajemenkursus.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/peserta")
public class PesertaController {
    private final PesertaService pesertaService;
    private final KursusService kursusService;

    public PesertaController(PesertaService ps, KursusService ks) {
        this.pesertaService = ps;
        this.kursusService = ks;
    }

    @PostMapping
    public Peserta tambah(@RequestBody Peserta peserta) {
        return pesertaService.tambah(peserta);
    }

    @GetMapping
    public List<Peserta> getAll() {
        return pesertaService.getAll();
    }

    @PostMapping("/{id}/daftar/{kodeKursus}")
    public ResponseEntity<String> daftar(@PathVariable String id, @PathVariable String kodeKursus) {
        Optional<Peserta> peserta = pesertaService.findById(id);
        if (peserta.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Peserta tidak ditemukan");
        }

        boolean berhasil = kursusService.daftarPeserta(kodeKursus, peserta.get());
        if (berhasil) {
            return ResponseEntity.ok("Pendaftaran berhasil");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Kursus penuh atau tidak ditemukan");
        }
    }
}
