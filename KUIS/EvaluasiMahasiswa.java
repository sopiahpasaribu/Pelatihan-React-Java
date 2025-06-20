public class EvaluasiMahasiswa {
    public static void main(String[] args) {
        java.util.Scanner input = new java.util.Scanner(System.in);
        String ulang;

        do {
            System.out.print("Masukkan nama mahasiswa: ");
            String nama = input.nextLine();

            System.out.print("Masukkan nilai akhir: ");
            int nilai = input.nextInt();
            input.nextLine(); // Membuang newline

            String status = (nilai >= 60) ? "Lulus" : "Tidak Lulus";

            String kategori;
            if (nilai >= 85) {
                kategori = "A";
            } else if (nilai >= 70) {
                kategori = "B";
            } else if (nilai >= 60) {
                kategori = "C";
            } else if (nilai >= 40) {
                kategori = "D";
            } else {
                kategori = "E";
            }

            System.out.println("\n--- Hasil Evaluasi ---");
            System.out.println("Nama     : " + nama);
            System.out.println("Nilai    : " + nilai);
            System.out.println("Status   : " + status);
            System.out.println("Kategori : " + kategori);

            System.out.print("\nIngin cek mahasiswa lain? (y/n): ");
            ulang = input.nextLine();
            System.out.println();
        } while (ulang.equalsIgnoreCase("y"));

        input.close(); 
        System.out.println("Terima kasih!");
    }
}
