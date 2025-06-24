package com.example.manajemenkursus.service;

import com.example.manajemenkursus.model.Peserta;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class PesertaService {
    private final List<Peserta> pesertaList = new ArrayList<>();

    public Peserta tambah(Peserta peserta) {
        pesertaList.add(peserta);
        return peserta;
    }

    public List<Peserta> getAll() {
        return pesertaList;
    }

    public Optional<Peserta> findById(String id) {
        return pesertaList.stream().filter(p -> p.getId().equals(id)).findFirst();
    }
}