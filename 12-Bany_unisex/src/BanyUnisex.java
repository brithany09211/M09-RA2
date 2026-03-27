import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class BanyUnisex {
    private final int BANY_BUIT = 0;
    private final int BANY_AMB_HOMES = 1;
    private final int BANY_AMB_DONES = 2;
    private final int CAPACITAT_MAX = 3;
    private static int estatActual;
    private Semaphore capacitat = new Semaphore(CAPACITAT_MAX, true);
    private ReentrantLock lockEstat = new ReentrantLock(true);
    private int ocupants = 0;

    public void estatActual(boolean ESTAT) {
        ESTAT= true;
    }

    public void ocupants(int num_ocupants) {

    }

    public void capacitat() {

    }

    public void utilitzaLavabo() throws InterruptedException {

    }

    //homes:
    public void entraHome() throws InterruptedException {
        while(true) {
            lockEstat.lock();
            try {
                if (estatActual == BANY_BUIT || estatActual == BANY_AMB_HOMES) {
                    if (capacitat.tryAcquire()) {  
                        ocupants++;
                        estatActual = BANY_AMB_HOMES;
                        System.out.println("Home entra al bany. Ocupants: " + ocupants);
                        return;  
                    }
                }
            } finally {
                lockEstat.unlock();
            }
            Thread.sleep(100);
        }
    }

    public void surtHome() throws InterruptedException {

    }

    //Part de Dona:
    public void entraDona() throws InterruptedException {
        while(true) {
            lockEstat.lock();
            try {
                if (estatActual == BANY_BUIT || estatActual == BANY_AMB_DONES) {
                    if (capacitat.tryAcquire()) {  
                        ocupants++;
                        estatActual = BANY_AMB_DONES;
                        System.out.println("Dona entra al bany. Ocupants: " + ocupants);
                        return;  
                    }
                }
            } finally {
                lockEstat.unlock();
            }
            Thread.sleep(100);
        }
    }

    public void surtDona() throws InterruptedException {

    }

    public static void main(String[] args) {
        int numPersones = 5;
        for (int i = 1; i < numPersones; i++) {
            Home home = new Home("Home", null);
            Dona dona = new Dona("Dona", null);
        }
        
    }
}
