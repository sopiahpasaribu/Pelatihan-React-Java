package com.example.mahasiswa;

public class Mahasiswa {
    private String id;
    private String nama;
    private String jurusan;
    private int angkatan;

    // Constructors
    public Mahasiswa() {}

    public Mahasiswa(String id, String nama, String jurusan, int angkatan) {
        this.id = id;
        this.nama = nama;
        this.jurusan = jurusan;
        this.angkatan = angkatan;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public int getAngkatan() {
        return angkatan;
    }

    public void setAngkatan(int angkatan) {
        this.angkatan = angkatan;
    }
}