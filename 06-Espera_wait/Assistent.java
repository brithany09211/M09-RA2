import java.util.Random;

public class Assistent extends Thread {
    private Esdeveniment esdeveniment;
    private String nom;
    private Random rnd;

    public Assistent (String nom, Esdeveniment esdeveniment) {
        this.nom = nom;
        this.esdeveniment = esdeveniment;
        this.rnd = new Random();
    }

    @Override
    public void run() {
        while (true) {
            if (rnd.nextBoolean()) {
                esdeveniment.ferReserva(this);
            } 
            else {
                esdeveniment.cancelaReserva(this);
            }
            try {
                Thread.sleep(rnd.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public String getNom() {
        return nom;
    }
    
}
