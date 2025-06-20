package com.example.manajemenkaryawanproyek.service;

import com.example.manajemenkaryawanproyek.model.Proyek;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProyekService {
    private final List<Proyek> proyekList = new ArrayList<>();

    public List<Proyek> getAllProyek() {
        return Collections.unmodifiableList(proyekList);
    }

    public Optional<Proyek> getProyekByKode(String kode) {
        return proyekList.stream().filter(p -> p.getKode().equals(kode)).findFirst();
    }

    public String addProyek(Proyek proyek) {
        proyekList.add(proyek);
        return "Proyek berhasil ditambahkan";
    }

    public String updateProyek(String kode, Proyek proyekUpdate) {
        for (int i = 0; i < proyekList.size(); i++) {
            if (proyekList.get(i).getKode().equals(kode)) {
                proyekList.set(i, proyekUpdate);
                return "Proyek berhasil diupdate";
            }
        }
        return "Proyek tidak ditemukan";
    }

    public String deleteProyek(String kode) {
        proyekList.removeIf(p -> p.getKode().equals(kode));
        return "Proyek berhasil dihapus";
    }

    public boolean isProyekExist(String kode) {
        return proyekList.stream().anyMatch(p -> p.getKode().equals(kode));
    }
}
