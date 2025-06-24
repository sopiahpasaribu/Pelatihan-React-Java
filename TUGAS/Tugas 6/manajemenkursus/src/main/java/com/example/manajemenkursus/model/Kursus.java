package com.example.manajemenkursus.model;

import java.util.*;

public class Kursus {
    private String kode = UUID.randomUUID().toString();
    private String namaKursus;
    private int kuota;
    private Instruktur instruktur;
    private List<Peserta> pesertaList = new ArrayList<>();

    // Getter & Setter
    public String getKode() { return kode; }
    public String getNamaKursus() { return namaKursus; }
    public void setNamaKursus(String namaKursus) { this.namaKursus = namaKursus; }
    public int getKuota() { return kuota; }
    public void setKuota(int kuota) { this.kuota = kuota; }
    public Instruktur getInstruktur() { return instruktur; }
    public void setInstruktur(Instruktur instruktur) { this.instruktur = instruktur; }
    public List<Peserta> getPesertaList() { return pesertaList; }
}