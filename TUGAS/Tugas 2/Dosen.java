public class Dosen extends Person {
    private String bidang;

    public Dosen(String nama, String nip, String bidang) {
        super(nama, nip);
        this.bidang = bidang;
    }

    @Override
    public void display() {
        System.out.println("Nama: " + nama);
        System.out.println("NIP: " + id);
        System.out.println("Bidang: " + bidang);
    }
}
