import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class BanyUnisex {
    
    private final int BANY_BUIT = 0;
    private final int BANY_AMB_HOMES = 1;
    private final int BANY_AMB_DONES = 2;
    private final int CAPACITAT_MAX = 3;
    private static int estatActual;
    private Semaphore capacitat;
    private ReentrantLock lockEstat;
    private int ocupants = 0;

    public BanyUnisex() {
        this.estatActual = BANY_BUIT;
        this.ocupants = 0;
        this.capacitat = new Semaphore(CAPACITAT_MAX, true);
        this.lockEstat = new ReentrantLock(true);
    }

    //homes:
    public void entraHome() {
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
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;  
            }
        }
    }

    public void surtHome() {
        lockEstat.lock();
        try {
            ocupants--; 
            capacitat.release();
            System.out.println("Home surt del bany. Ocupants: " + ocupants);
            
            if (ocupants == 0) {
                estatActual = BANY_BUIT;
                System.out.println("El bany esta buit");
            }
        } finally {
            lockEstat.unlock();
        }
    }

    //Part de Dona:
    public void entraDona() {
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
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;  
            }
        }
    }

    public void surtDona() {
        lockEstat.lock();
        try {
            ocupants--;
            capacitat.release();
            System.out.println("Dona surt del bany. Ocupants: " + ocupants);
            
            if (ocupants == 0) {
                estatActual = BANY_BUIT;
                System.out.println("El bany esta buit");
            }
        } finally {
            lockEstat.unlock();
        }
    }

    public static void main(String[] args) {
        BanyUnisex bany = new BanyUnisex();

        for (int i = 0; i < 5; i++) {
            new Home("Home-" + i, bany).start();
            new Dona("Dona-" + i, bany).start();
        }
    }
}
