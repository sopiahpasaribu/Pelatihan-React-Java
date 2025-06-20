import java.util.ArrayList;
import java.util.Scanner;

public class ManajemenNilaiMahasiswa {
    static Scanner input = new Scanner(System.in);
    static ArrayList<Mahasiswa> daftarMahasiswa = new ArrayList<>();

    public static void main(String[] args) {
        int pilihan;

        do {
            System.out.println("\n=== MENU MANAJEMEN NILAI MAHASISWA ===");
            System.out.println("1. Input Data Mahasiswa");
            System.out.println("2. Tampilkan Data Mahasiswa");
            System.out.println("3. Cari Mahasiswa (berdasarkan NIM)");
            System.out.println("4. Hapus Mahasiswa (berdasarkan NIM)");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = input.nextInt();
            input.nextLine(); 

            switch (pilihan) {
                case 1:
                    inputData();
                    break;
                case 2:
                    tampilData();
                    break;
                case 3:
                    cariMahasiswa();
                    break;
                case 4:
                    hapusMahasiswa();
                    break;
                case 5:
                    System.out.println("Terima kasih!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 5);
    }

    static void inputData() {
        System.out.print("Masukkan jumlah mahasiswa: ");
        int jumlah = input.nextInt();
        input.nextLine();

        for (int i = 0; i < jumlah; i++) {
            System.out.println("\nMahasiswa ke-" + (i + 1));
            System.out.print("Nama        : ");
            String nama = input.nextLine();
            System.out.print("NIM         : ");
            String nim = input.nextLine();

            double tugas = inputNilai("Tugas");
            double uts = inputNilai("UTS");
            double uas = inputNilai("UAS");

            double nilaiAkhir = hitungNilaiAkhir(tugas, uts, uas);
            char grade = tentukanGrade(nilaiAkhir);

            daftarMahasiswa.add(new Mahasiswa(nama, nim, nilaiAkhir, grade));
        }
    }

    static double inputNilai(String jenis) {
        double nilai;
        while (true) {
            System.out.print("Nilai " + jenis + " : ");
            nilai = input.nextDouble();
            input.nextLine();
            if (nilai >= 0 && nilai <= 100) break;
            System.out.println("Nilai harus antara 0 - 100!");
        }
        return nilai;
    }

    static double hitungNilaiAkhir(double tugas, double uts, double uas) {
        return (0.3 * tugas) + (0.3 * uts) + (0.4 * uas);
    }

    static char tentukanGrade(double nilai) {
        if (nilai >= 85) return 'A';
        else if (nilai >= 75) return 'B';
        else if (nilai >= 65) return 'C';
        else if (nilai >= 50) return 'D';
        else return 'E';
    }

    static void tampilData() {
        if (daftarMahasiswa.isEmpty()) {
            System.out.println("\nBelum ada data mahasiswa.");
            return;
        }

        System.out.println("\n=== DATA MAHASISWA ===");
        int i = 1;
        for (Mahasiswa mhs : daftarMahasiswa) {
            System.out.println("Mahasiswa ke-" + i);
            System.out.println("Nama        : " + mhs.nama);
            System.out.println("NIM         : " + mhs.nim);
            System.out.println("Nilai Akhir : " + mhs.nilaiAkhir);
            System.out.println("Grade       : " + mhs.grade);
            System.out.println("------------------------------");
            i++;
        }
    }

    static void cariMahasiswa() {
        System.out.print("Masukkan NIM yang dicari: ");
        String nimCari = input.nextLine();
        boolean ditemukan = false;

        for (Mahasiswa mhs : daftarMahasiswa) {
            if (mhs.nim.equalsIgnoreCase(nimCari)) {
                System.out.println("\nData Mahasiswa Ditemukan:");
                System.out.println("Nama        : " + mhs.nama);
                System.out.println("NIM         : " + mhs.nim);
                System.out.println("Nilai Akhir : " + mhs.nilaiAkhir);
                System.out.println("Grade       : " + mhs.grade);
                ditemukan = true;
                break;
            }
        }

        if (!ditemukan) {
            System.out.println("Mahasiswa dengan NIM " + nimCari + " tidak ditemukan.");
        }
    }

    static void hapusMahasiswa() {
        System.out.print("Masukkan NIM yang ingin dihapus: ");
        String nimHapus = input.nextLine();
        boolean dihapus = false;

        for (int i = 0; i < daftarMahasiswa.size(); i++) {
            if (daftarMahasiswa.get(i).nim.equalsIgnoreCase(nimHapus)) {
                daftarMahasiswa.remove(i);
                dihapus = true;
                System.out.println("Data mahasiswa dengan NIM " + nimHapus + " berhasil dihapus.");
                break;
            }
        }

        if (!dihapus) {
            System.out.println("Mahasiswa dengan NIM " + nimHapus + " tidak ditemukan.");
        }
    }
}

class Mahasiswa {
    String nama;
    String nim;
    double nilaiAkhir;
    char grade;

    Mahasiswa(String nama, String nim, double nilaiAkhir, char grade) {
        this.nama = nama;
        this.nim = nim;
        this.nilaiAkhir = nilaiAkhir;
        this.grade = grade;
    }
}
