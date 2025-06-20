package com.example.buku;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BukuService {
    private final List<Buku> daftarBuku = new ArrayList<>();

    public Buku tambahBuku(Buku buku) {
        daftarBuku.add(buku);
        return buku;
    }

    public List<Buku> getAllBuku() {
        return daftarBuku;
    }

    public Optional<Buku> getBukuById(String id) {
        return daftarBuku.stream()
                .filter(buku -> buku.getId().equals(id))
                .findFirst();
    }

    public Buku updateBuku(String id, Buku bukuUpdate) {
        Optional<Buku> bukuOptional = getBukuById(id);
        if (bukuOptional.isPresent()) {
            Buku buku = bukuOptional.get();
            buku.setJudul(bukuUpdate.getJudul());
            buku.setPenulis(bukuUpdate.getPenulis());
            buku.setTahunTerbit(bukuUpdate.getTahunTerbit());
            return buku;
        }
        return null;
    }

    public boolean hapusBuku(String id) {
        Optional<Buku> bukuOptional = getBukuById(id);
        if (bukuOptional.isPresent()) {
            daftarBuku.remove(bukuOptional.get());
            return true;
        }
        return false;
    }
}