public class Mahasiswa extends Person {
    private String nim;

    // Constructor
    public Mahasiswa(String nama, int umur, String nim) {
        super(nama, umur);
        this.nim = nim;
    }

    // Getter dan Setter untuk NIM
    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    // Override method displayInfo
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("NIM: " + nim);
        System.out.println("----------------------");
    }
}