package com.example.manajemenkursus.model;

import java.util.*;

public class Peserta {
    private String id = UUID.randomUUID().toString();
    private String nama;
    private String email;
    private List<Kursus> kursusDiikuti = new ArrayList<>();

    // Getter & Setter
    public String getId() { return id; }
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public List<Kursus> getKursusDiikuti() { return kursusDiikuti; }
}