package com.example.manajemenkaryawanproyek.controller;

import com.example.manajemenkaryawanproyek.model.Karyawan;
import com.example.manajemenkaryawanproyek.service.DataService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/karyawan")
public class KaryawanController {

    private final DataService dataService;

    public KaryawanController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping
    public List<Karyawan> getAllKaryawan() {
        return dataService.getAllKaryawan();
    }

    @GetMapping("/{id}")
    public Karyawan getKaryawanById(@PathVariable String id) {
        return dataService.getKaryawanById(id).orElse(null);
    }

    @PostMapping
    public String addKaryawan(@RequestBody Karyawan karyawan) {
        return dataService.addKaryawan(karyawan);
    }

    @PutMapping("/{id}")
    public String updateKaryawan(@PathVariable String id, @RequestBody Karyawan karyawanUpdate) {
        return dataService.updateKaryawan(id, karyawanUpdate);
    }

    @DeleteMapping("/{id}")
    public String deleteKaryawan(@PathVariable String id) {
        return dataService.deleteKaryawan(id);
    }
}
