package com.example.mahasiswa;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MahasiswaService {
    private final List<Mahasiswa> daftarMahasiswa = new ArrayList<>();

    public Mahasiswa simpanMahasiswa(Mahasiswa mahasiswa) {
        daftarMahasiswa.add(mahasiswa);
        return mahasiswa;
    }

    public List<Mahasiswa> getAllMahasiswa() {
        return daftarMahasiswa;
    }

    public Optional<Mahasiswa> getMahasiswaById(String id) {
        return daftarMahasiswa.stream()
                .filter(mhs -> mhs.getId().equals(id))
                .findFirst();
    }

    public Mahasiswa updateMahasiswa(String id, Mahasiswa mahasiswaUpdate) {
        Optional<Mahasiswa> mhsOptional = getMahasiswaById(id);
        if (mhsOptional.isPresent()) {
            Mahasiswa mhs = mhsOptional.get();
            mhs.setNama(mahasiswaUpdate.getNama());
            mhs.setJurusan(mahasiswaUpdate.getJurusan());
            mhs.setAngkatan(mahasiswaUpdate.getAngkatan());
            return mhs;
        }
        return null;
    }

    public boolean hapusMahasiswa(String id) {
        Optional<Mahasiswa> mhsOptional = getMahasiswaById(id);
        if (mhsOptional.isPresent()) {
            daftarMahasiswa.remove(mhsOptional.get());
            return true;
        }
        return false;
    }
}