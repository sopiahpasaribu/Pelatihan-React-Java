package com.example.manajemenkaryawanproyek.controller;

import com.example.manajemenkaryawanproyek.model.Proyek;
import com.example.manajemenkaryawanproyek.service.ProyekService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proyek")
public class ProyekController {

    private final ProyekService proyekService;

    public ProyekController(ProyekService proyekService) {
        this.proyekService = proyekService;
    }

    @GetMapping
    public List<Proyek> getAllProyek() {
        return proyekService.getAllProyek();
    }

    @GetMapping("/{kode}")
    public Proyek getProyekByKode(@PathVariable String kode) {
        return proyekService.getProyekByKode(kode).orElse(null);
    }

    @PostMapping
    public String addProyek(@RequestBody Proyek proyek) {
        return proyekService.addProyek(proyek);
    }

    @PutMapping("/{kode}")
    public String updateProyek(@PathVariable String kode, @RequestBody Proyek proyekUpdate) {
        return proyekService.updateProyek(kode, proyekUpdate);
    }

    @DeleteMapping("/{kode}")
    public String deleteProyek(@PathVariable String kode) {
        return proyekService.deleteProyek(kode);
    }
}
