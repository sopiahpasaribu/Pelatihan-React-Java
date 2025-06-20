import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Mahasiswa> daftarMahasiswa = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n=== Sistem Manajemen Mahasiswa ===");
            System.out.println("1. Tambah Mahasiswa");
            System.out.println("2. Tampilkan Semua Mahasiswa");
            System.out.println("3. Update Data Mahasiswa");
            System.out.println("4. Hapus Mahasiswa");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu (1-5): ");

            int pilihan = scanner.nextInt();
            scanner.nextLine(); 

            switch (pilihan) {
                case 1:
                    tambahMahasiswa();
                    break;
                case 2:
                    tampilkanMahasiswa();
                    break;
                case 3:
                    updateMahasiswa();
                    break;
                case 4:
                    hapusMahasiswa();
                    break;
                case 5:
                    isRunning = false;
                    System.out.println("Terima kasih telah menggunakan sistem ini.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private static void tambahMahasiswa() {
        System.out.println("\n=== Tambah Mahasiswa Baru ===");
        System.out.print("Masukkan Nama: ");
        String nama = scanner.nextLine();

        System.out.print("Masukkan Umur: ");
        int umur = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Masukkan NIM: ");
        String nim = scanner.nextLine();

        Mahasiswa mhs = new Mahasiswa(nama, umur, nim);
        daftarMahasiswa.add(mhs);

        System.out.println("Mahasiswa berhasil ditambahkan!");
    }

    private static void tampilkanMahasiswa() {
        if (daftarMahasiswa.isEmpty()) {
            System.out.println("\nBelum ada data mahasiswa.");
            return;
        }

        System.out.println("\n=== Daftar Mahasiswa ===");
        for (int i = 0; i < daftarMahasiswa.size(); i++) {
            System.out.println("Mahasiswa #" + (i + 1));
            daftarMahasiswa.get(i).displayInfo();
        }
    }

    private static void updateMahasiswa() {
        if (daftarMahasiswa.isEmpty()) {
            System.out.println("\nBelum ada data mahasiswa untuk diupdate.");
            return;
        }

        tampilkanMahasiswa();
        System.out.print("\nPilih nomor mahasiswa yang ingin diupdate: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine(); 

        if (index < 0 || index >= daftarMahasiswa.size()) {
            System.out.println("Nomor tidak valid!");
            return;
        }

        Mahasiswa mhs = daftarMahasiswa.get(index);

        System.out.println("\nMasukkan data baru (kosongkan jika tidak ingin mengubah):");
        System.out.print("Nama (" + mhs.getNama() + "): ");
        String nama = scanner.nextLine();
        if (!nama.isEmpty()) {
            mhs.setNama(nama);
        }

        System.out.print("Umur (" + mhs.getUmur() + "): ");
        String umurStr = scanner.nextLine();
        if (!umurStr.isEmpty()) {
            mhs.setUmur(Integer.parseInt(umurStr));
        }

        System.out.print("NIM (" + mhs.getNim() + "): ");
        String nim = scanner.nextLine();
        if (!nim.isEmpty()) {
            mhs.setNim(nim);
        }
        

        System.out.println("Data mahasiswa berhasil diupdate!");
    }

    private static void hapusMahasiswa() {
        if (daftarMahasiswa.isEmpty()) {
            System.out.println("\nBelum ada data mahasiswa untuk dihapus.");
            return;
        }

        tampilkanMahasiswa();
        System.out.print("\nPilih nomor mahasiswa yang ingin dihapus: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine(); 

        if (index < 0 || index >= daftarMahasiswa.size()) {
            System.out.println("Nomor tidak valid!");
            return;
        }

        daftarMahasiswa.remove(index);
        System.out.println("Mahasiswa berhasil dihapus!");
    }
}