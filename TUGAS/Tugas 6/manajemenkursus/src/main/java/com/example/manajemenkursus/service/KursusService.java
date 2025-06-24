package com.example.manajemenkursus.service;

import com.example.manajemenkursus.model.*;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class KursusService {
    private final List<Kursus> kursusList = new ArrayList<>();

    public Kursus tambah(Kursus kursus) {
        kursusList.add(kursus);
        return kursus;
    }

    public List<Kursus> getAll() {
        return kursusList;
    }

    public Optional<Kursus> findByKode(String kode) {
        return kursusList.stream().filter(k -> k.getKode().equals(kode)).findFirst();
    }

    public boolean daftarPeserta(String kode, Peserta peserta) {
        Optional<Kursus> opt = findByKode(kode);
        if (opt.isPresent()) {
            Kursus kursus = opt.get();
            if (kursus.getPesertaList().size() < kursus.getKuota()) {
                kursus.getPesertaList().add(peserta);
                peserta.getKursusDiikuti().add(kursus);
                return true;
            }
        }
        return false;
    }
}