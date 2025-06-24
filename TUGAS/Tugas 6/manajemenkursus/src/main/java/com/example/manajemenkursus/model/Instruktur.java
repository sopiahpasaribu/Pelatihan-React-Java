package com.example.manajemenkursus.model;

import java.util.UUID;

public class Instruktur {
    private String id = UUID.randomUUID().toString();
    private String nama;
    private String keahlian;

    // Getter & Setter
    public String getId() { return id; }
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    public String getKeahlian() { return keahlian; }
    public void setKeahlian(String keahlian) { this.keahlian = keahlian; }
}