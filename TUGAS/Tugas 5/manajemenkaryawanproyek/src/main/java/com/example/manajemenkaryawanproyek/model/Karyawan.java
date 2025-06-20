package com.example.manajemenkaryawanproyek.model;

import lombok.Data;
import java.util.List;

@Data
public class Karyawan {
    private String id;
    private String nama;
    private String jabatan;
    private List<String> kodeProyek; // karena bisa lebih dari satu proyek
}
