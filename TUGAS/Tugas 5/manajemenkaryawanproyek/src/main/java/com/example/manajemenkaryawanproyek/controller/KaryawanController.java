package com.example.manajemenkaryawanproyek.controller;

import com.example.manajemenkaryawanproyek.model.Karyawan;
import com.example.manajemenkaryawanproyek.service.KaryawanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/karyawan")
public class KaryawanController {

    private final KaryawanService karyawanService;

    public KaryawanController(KaryawanService karyawanService) {
        this.karyawanService = karyawanService;
    }

    @GetMapping
    public List<Karyawan> getAllKaryawan() {
        return karyawanService.getAllKaryawan();
    }

    @GetMapping("/{id}")
    public Karyawan getKaryawanById(@PathVariable String id) {
        return karyawanService.getKaryawanById(id).orElse(null);
    }

    @PostMapping
    public String addKaryawan(@RequestBody Karyawan karyawan) {
        return karyawanService.addKaryawan(karyawan);
    }

    @PutMapping("/{id}")
    public String updateKaryawan(@PathVariable String id, @RequestBody Karyawan karyawanUpdate) {
        return karyawanService.updateKaryawan(id, karyawanUpdate);
    }

    @DeleteMapping("/{id}")
    public String deleteKaryawan(@PathVariable String id) {
        return karyawanService.deleteKaryawan(id);
    }
}
