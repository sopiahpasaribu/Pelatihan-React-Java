import java.util.ArrayList;

interface BisaBertarung {
    void serang(Monster target);
    void serang(Monster target, String jurus);
}

interface BisaMenyembuhkan {
    void menyembuhkan(int jumlah);
}

abstract class Monster {
    protected String nama;
    protected int kesehatan;
    protected int kekuatan;
    
    public Monster(String nama, int kesehatan, int kekuatan) {
        this.nama = nama;
        this.kesehatan = kesehatan;
        this.kekuatan = kekuatan;
    }
    
    public abstract void tampilkanInfo();
    
    public void terimaSerangan(int damage) {
        this.kesehatan -= damage;
        if (this.kesehatan < 0) {
            this.kesehatan = 0;
        }
    }
    
    public boolean masihHidup() {
        return this.kesehatan > 0;
    }
    
    public String getNama() {
        return nama;
    }
    
    public int getKesehatan() {
        return kesehatan;
    }
    
    public int getKekuatan() {
        return kekuatan;
    }
}

class MonsterApi extends Monster implements BisaBertarung {
    public MonsterApi(String nama, int kesehatan, int kekuatan) {
        super(nama, kesehatan, kekuatan);
    }
    
    @Override
    public void tampilkanInfo() {
        System.out.println("Monster Api: " + nama + 
                         " | HP: " + kesehatan + 
                         " | ATK: " + kekuatan);
    }
    
    @Override
    public void serang(Monster target) {
        System.out.println(nama + " menyerang " + target.getNama() + " dengan serangan api biasa!");
        target.terimaSerangan(kekuatan);
    }
    
    @Override
    public void serang(Monster target, String jurus) {
        int damage = kekuatan + (jurus.length() * 2); 
        System.out.println(nama + " menggunakan jurus '" + jurus + "' terhadap " + target.getNama() + "!");
        target.terimaSerangan(damage);
    }
}

class MonsterAir extends Monster implements BisaBertarung, BisaMenyembuhkan {
    public MonsterAir(String nama, int kesehatan, int kekuatan) {
        super(nama, kesehatan, kekuatan);
    }
    
    @Override
    public void tampilkanInfo() {
        System.out.println("Monster Air: " + nama + 
                         " | HP: " + kesehatan + 
                         " | ATK: " + kekuatan);
    }
    
    @Override
    public void serang(Monster target) {
        System.out.println(nama + " menyerang " + target.getNama() + " dengan semburan air!");
        target.terimaSerangan(kekuatan);
    }
    
    @Override
    public void serang(Monster target, String jurus) {
        int damage = kekuatan + (jurus.length() * 3); 
        System.out.println(nama + " menggunakan jurus air '" + jurus + "' terhadap " + target.getNama() + "!");
        target.terimaSerangan(damage);
    }
    
    @Override
    public void menyembuhkan(int jumlah) {
        kesehatan += jumlah;
        System.out.println(nama + " menyembuhkan diri sebesar " + jumlah + " HP!");
    }
}

class MonsterListrik extends Monster implements BisaBertarung {
    public MonsterListrik(String nama, int kesehatan, int kekuatan) {
        super(nama, kesehatan, kekuatan);
    }
    
    @Override
    public void tampilkanInfo() {
        System.out.println("Monster Listrik: " + nama + 
                         " | HP: " + kesehatan + 
                         " | ATK: " + kekuatan);
    }
    
    @Override
    public void serang(Monster target) {
        System.out.println(nama + " menyetrum " + target.getNama() + " dengan serangan listrik!");
        target.terimaSerangan(kekuatan);
    }
    
    @Override
    public void serang(Monster target, String jurus) {
        int damage = kekuatan + (jurus.length() * 4); 
        System.out.println(nama + " menggunakan jurus listrik '" + jurus + "' terhadap " + target.getNama() + "!");
        System.out.println("SYYYYYZZZZZT!!!");
        target.terimaSerangan(damage);
    }
}

public class ArenaPertarunganMonster {
    public static void main(String[] args) {
        ArrayList<Monster> arena = new ArrayList<>();
        
        arena.add(new MonsterApi("Agni", 100, 15));
        arena.add(new MonsterAir("Aqua", 120, 10));
        arena.add(new MonsterListrik("Volt", 80, 20));
        arena.add(new MonsterAir("Tidal", 90, 12));
        arena.add(new MonsterListrik("Zap", 70, 25));
        
        System.out.println("===== DAFTAR MONSTER DI ARENA =====");
        for (Monster m : arena) {
            m.tampilkanInfo();
        }
        System.out.println();
        
        System.out.println("===== PERTARUNGAN DIMULAI! =====");
        
        ((BisaBertarung)arena.get(0)).serang(arena.get(1), "Fire Tornado");
        ((BisaBertarung)arena.get(1)).serang(arena.get(0));
        ((BisaBertarung)arena.get(2)).serang(arena.get(0), "Thunder Strike");
        ((BisaMenyembuhkan)arena.get(3)).menyembuhkan(20);
        ((BisaBertarung)arena.get(4)).serang(arena.get(1), "Lightning Bolt");
        System.out.println();
        
        System.out.println("===== STATUS AKHIR =====");
        for (Monster m : arena) {
            m.tampilkanInfo();
            System.out.println("Status: " + (m.masihHidup() ? "Masih hidup" : "Kalah"));
            System.out.println();
        }
    }
}