import java.util.*;

public class PenerapanOOPMahasiswaDosen {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Mahasiswa> listMahasiswa = new ArrayList<>();

        System.out.print("Masukkan jumlah mahasiswa: ");
        int jmlMhs = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < jmlMhs; i++) {
            System.out.println("Masukkan data mahasiswa ke-" + (i + 1));
            System.out.print("Nama: ");
            String nama = scanner.nextLine();
            System.out.print("NIM: ");
            String nim = scanner.nextLine();
            System.out.print("Jurusan: ");
            String jurusan = scanner.nextLine();

            listMahasiswa.add(new Mahasiswa(nama, nim, jurusan));
        }

        HashMap<String, Dosen> mapDosen = new HashMap<>();

        System.out.print("Masukkan jumlah dosen: ");
        int jmlDosen = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < jmlDosen; i++) {
            System.out.println("Masukkan data dosen ke-" + (i + 1));
            System.out.print("Nama: ");
            String nama = scanner.nextLine();
            System.out.print("NIP: ");
            String nip = scanner.nextLine();
            System.out.print("Bidang: ");
            String bidang = scanner.nextLine();

            mapDosen.put(nip, new Dosen(nama, nip, bidang));
        }

        System.out.println("\n===== Daftar Mahasiswa =====");
        for (Mahasiswa m : listMahasiswa) {
            m.display();
            System.out.println();
        }

        System.out.println("====== Daftar Dosen ======");
        for (String nip : mapDosen.keySet()) {
            mapDosen.get(nip).display();
            System.out.println();
        }
        scanner.close();
    }
}
