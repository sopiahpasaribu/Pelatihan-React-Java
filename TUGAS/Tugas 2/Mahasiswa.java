public class Mahasiswa extends Person {
    private String jurusan;

    public Mahasiswa(String nama, String nim, String jurusan) {
        super(nama, nim);
        this.jurusan = jurusan;
    }

    @Override
    public void display() {
        System.out.println("Nama: " + nama);
        System.out.println("NIM: " + id);
        System.out.println("Jurusan: " + jurusan);
    }
}
