import java.util.Random;

public class Soci extends Thread {
    private String nom;
    private Compte compte;
    private int aportacio;
    private int esperaMax;
    private Random random;
    private int maxAnys;
    
    public Soci(String nom, Compte compte, int aportacio, int esperaMax) {
        this.nom = nom;
        this.compte = compte;
        this.aportacio = aportacio;
        this.esperaMax = esperaMax;
        this.random = new Random();
        this.maxAnys = 10; // Per defecte
    }
    
    @Override
    public void run() {
        compte.ingres(aportacio);
        
        try {
            int delay = random.nextInt(esperaMax + 1);
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        compte.treu(aportacio);
    }
    
    public Compte getCompte() {
        return compte;
    }
}