package com.example.manajemenkaryawanproyek.service;

import com.example.manajemenkaryawanproyek.model.Karyawan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class KaryawanService {
    private final List<Karyawan> karyawanList = new ArrayList<>();

    @Autowired
    private ProyekService proyekService;

    public List<Karyawan> getAllKaryawan() {
        return Collections.unmodifiableList(karyawanList);
    }

    public Optional<Karyawan> getKaryawanById(String id) {
        return karyawanList.stream().filter(k -> k.getId().equals(id)).findFirst();
    }

    public String addKaryawan(Karyawan karyawan) {
        for (String kode : karyawan.getKodeProyek()) {
            if (!proyekService.isProyekExist(kode)) {
                return "Gagal menambahkan karyawan: Kode proyek tidak valid (" + kode + ")";
            }
        }
        karyawanList.add(karyawan);
        return "Karyawan berhasil ditambahkan";
    }

    public String updateKaryawan(String id, Karyawan karyawanUpdate) {
        for (int i = 0; i < karyawanList.size(); i++) {
            if (karyawanList.get(i).getId().equals(id)) {
                for (String kode : karyawanUpdate.getKodeProyek()) {
                    if (!proyekService.isProyekExist(kode)) {
                        return "Gagal mengupdate karyawan: Kode proyek tidak valid (" + kode + ")";
                    }
                }
                karyawanList.set(i, karyawanUpdate);
                return "Karyawan berhasil diupdate";
            }
        }
        return "Karyawan tidak ditemukan";
    }

    public String deleteKaryawan(String id) {
        karyawanList.removeIf(k -> k.getId().equals(id));
        return "Karyawan berhasil dihapus";
    }
}
