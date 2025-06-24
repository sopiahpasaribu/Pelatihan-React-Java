package com.example.manajemenkursus.service;

import com.example.manajemenkursus.model.Instruktur;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class InstrukturService {
    private final List<Instruktur> instrukturList = new ArrayList<>();

    public Instruktur tambah(Instruktur instruktur) {
        instrukturList.add(instruktur);
        return instruktur;
    }

    public List<Instruktur> getAll() {
        return instrukturList;
    }

    public Optional<Instruktur> findById(String id) {
        return instrukturList.stream().filter(i -> i.getId().equals(id)).findFirst();
    }
}
