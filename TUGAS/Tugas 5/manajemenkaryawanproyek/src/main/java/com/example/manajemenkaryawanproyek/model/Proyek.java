package com.example.manajemenkaryawanproyek.model;

import lombok.Data;

@Data
public class Proyek {
    private String kode;
    private String nama;
    private String deskripsi;
    private String tanggalMulai;
    private String tanggalSelesai;
}