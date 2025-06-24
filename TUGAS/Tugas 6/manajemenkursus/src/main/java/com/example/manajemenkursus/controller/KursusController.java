package com.example.manajemenkursus.controller;

import com.example.manajemenkursus.model.*;
import com.example.manajemenkursus.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/kursus")
public class KursusController {
    private final KursusService kursusService;
    private final InstrukturService instrukturService;

    public KursusController(KursusService ks, InstrukturService is) {
        this.kursusService = ks;
        this.instrukturService = is;
    }

    @PostMapping
    public ResponseEntity<Object> tambah(@RequestParam String instrukturId, @RequestBody Kursus kursus) {
        Optional<Instruktur> instruktur = instrukturService.findById(instrukturId);
        if (instruktur.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Instruktur tidak ditemukan");
        }

        kursus.setInstruktur(instruktur.get());
        Kursus kursusBaru = kursusService.tambah(kursus);
        return ResponseEntity.status(HttpStatus.CREATED).body(kursusBaru);
    }

    @GetMapping
    public ResponseEntity<List<Kursus>> getAll() {
        List<Kursus> semuaKursus = kursusService.getAll();
        return ResponseEntity.ok(semuaKursus);
    }

    @GetMapping("/{kode}")
    public ResponseEntity<Object> getDetail(@PathVariable String kode) {
        Optional<Kursus> kursus = kursusService.findByKode(kode);
        return kursus.<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kursus tidak ditemukan"));
    }
}