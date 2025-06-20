package com.example.manajemenkaryawanproyek.model;

import lombok.Data;

@Data
public class Karyawan {
    private String id;
    private String nama;
    private String email;
    private String posisi;
    private String kodeProyek; // Relasi dengan proyek
}