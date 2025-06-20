package com.example.manajemenkaryawanproyek.service;

import com.example.manajemenkaryawanproyek.model.Karyawan;
import com.example.manajemenkaryawanproyek.model.Proyek;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DataService {
    private final List<Proyek> proyekList = new ArrayList<>();
    private final List<Karyawan> karyawanList = new ArrayList<>();

    public DataService() {
        initDummyData();
    }

    private void initDummyData() {
        // Dummy Proyek
        Proyek proyek1 = new Proyek();
        proyek1.setKode("P001");
        proyek1.setNama("Website E-Commerce");
        proyek1.setDeskripsi("Pembuatan sistem e-commerce berbasis web");
        proyek1.setTanggalMulai("2023-01-01");
        proyek1.setTanggalSelesai("2023-06-30");
        proyekList.add(proyek1);

        Proyek proyek2 = new Proyek();
        proyek2.setKode("P002");
        proyek2.setNama("Aplikasi Mobile");
        proyek2.setDeskripsi("Pengembangan aplikasi Android");
        proyek2.setTanggalMulai("2023-02-01");
        proyek2.setTanggalSelesai("2023-08-31");
        proyekList.add(proyek2);

        // Dummy Karyawan
        Karyawan karyawan1 = new Karyawan();
        karyawan1.setId("K001");
        karyawan1.setNama("John Doe");
        karyawan1.setEmail("john@example.com");
        karyawan1.setPosisi("Backend Developer");
        karyawan1.setKodeProyek("P001");
        karyawanList.add(karyawan1);

        Karyawan karyawan2 = new Karyawan();
        karyawan2.setId("K002");
        karyawan2.setNama("Jane Smith");
        karyawan2.setEmail("jane@example.com");
        karyawan2.setPosisi("Frontend Developer");
        karyawan2.setKodeProyek("P002");
        karyawanList.add(karyawan2);
    }

    // Proyek
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

    // Karyawan
    public List<Karyawan> getAllKaryawan() {
        return Collections.unmodifiableList(karyawanList);
    }

    public Optional<Karyawan> getKaryawanById(String id) {
        return karyawanList.stream().filter(k -> k.getId().equals(id)).findFirst();
    }

    public String addKaryawan(Karyawan karyawan) {
        if (!isProyekExist(karyawan.getKodeProyek())) {
            return "Gagal menambahkan karyawan: Kode proyek tidak valid";
        }
        karyawanList.add(karyawan);
        return "Karyawan berhasil ditambahkan";
    }

    public String updateKaryawan(String id, Karyawan karyawanUpdate) {
        for (int i = 0; i < karyawanList.size(); i++) {
            if (karyawanList.get(i).getId().equals(id)) {
                if (!isProyekExist(karyawanUpdate.getKodeProyek())) {
                    return "Gagal mengupdate karyawan: Kode proyek tidak valid";
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

    public boolean isProyekExist(String kode) {
        return proyekList.stream().anyMatch(p -> p.getKode().equals(kode));
    }
}
