public class Person {
    protected String nama;
    protected String id;

    public Person(String nama, String id) {
        this.nama = nama;
        this.id = id;
    }

    public void display() {
        System.out.println("Nama: " + nama);
        System.out.println("ID: " + id);
    }
}
