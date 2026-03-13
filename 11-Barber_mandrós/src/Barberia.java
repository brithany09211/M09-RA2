import java.util.LinkedList;
import java.util.Queue;

public class Barberia {
    private Queue<Client> salaEspera = new LinkedList<>();
    private int maxCadires;
    public Object barberCond1 = new Object();
    private static Barberia instancia;

    public Barberia(int maxCadires) {
        this.maxCadires = maxCadires;
        instancia = this;
    }

    public static Barberia getInstancia() {
        return instancia;
    }

    public synchronized Client seguentClient() {
        return salaEspera.poll(); //FIFO 
    }

    public synchronized void entrarClient(Client client) {
        if (salaEspera.size() < maxCadires) {
            salaEspera.add(client);
            System.out.println("Client " + client.getNom() + " en espera");
            synchronized (barberCond1) {
                barberCond1.notify();
            }
        } else {
            System.out.println("No queden cadires, client " + client.getNom() + " se'n va");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Barberia barberia = new Barberia(3);
 
        Barber barber = new Barber("Pepe");
        barber.start();
 
        for (int i = 1; i <= 10; i++) {
            barberia.entrarClient(new Client(i));
            Thread.sleep(500);
        }
 
        Thread.sleep(10000);
 
        for (int i = 11; i <= 20; i++) {
            barberia.entrarClient(new Client(i));
            Thread.sleep(500);
        }
    }
}