import java.util.Random;

public class Barber extends Thread {
    private String nom;
    private Random rnd = new Random();

    public Barber(String nom) {
        this.nom = nom;
    }

    @Override
    public void run() {
        Barberia barberia = Barberia.getInstancia();

        while (true) {
            Client client = barberia.seguentClient();

            if (client != null) {
                System.out.println("Li toca al client " + client.getNom());
                client.tallarseElCabell();
                try {
                    long esperar = 900 + (long)(rnd.nextDouble() * 100);
                    Thread.sleep(esperar);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            } else {
                System.out.println("Ningú en espera");
                System.out.println("Barber " + nom + " dormint");
                synchronized (barberia.barberCond1) {
                    try {
                        barberia.barberCond1.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }
}