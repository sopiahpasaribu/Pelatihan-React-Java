package com.example.manajemenkursus.controller;

import com.example.manajemenkursus.model.Instruktur;
import com.example.manajemenkursus.service.InstrukturService;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/instruktur")
public class InstrukturController {
    private final InstrukturService service;

    public InstrukturController(InstrukturService service) {
        this.service = service;
    }

    @PostMapping
    public Instruktur tambah(@RequestBody Instruktur instruktur) {
        return service.tambah(instruktur);
    }

    @GetMapping
    public List<Instruktur> getAll() {
        return service.getAll();
    }
}