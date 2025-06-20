package com.example.manajemenkaryawanproyek.controller;

import com.example.manajemenkaryawanproyek.model.Proyek;
import com.example.manajemenkaryawanproyek.service.DataService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proyek")
public class ProyekController {

    private final DataService dataService;

    public ProyekController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping
    public List<Proyek> getAllProyek() {
        return dataService.getAllProyek();
    }

    @GetMapping("/{kode}")
    public Proyek getProyekByKode(@PathVariable String kode) {
        return dataService.getProyekByKode(kode).orElse(null);
    }

    @PostMapping
    public String addProyek(@RequestBody Proyek proyek) {
        return dataService.addProyek(proyek);
    }

    @PutMapping("/{kode}")
    public String updateProyek(@PathVariable String kode, @RequestBody Proyek proyekUpdate) {
        return dataService.updateProyek(kode, proyekUpdate);
    }

    @DeleteMapping("/{kode}")
    public String deleteProyek(@PathVariable String kode) {
        return dataService.deleteProyek(kode);
    }
}
