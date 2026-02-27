import java.util.List;
import java.util.Random;

public class Estanc extends Thread {
    public List<String> tabac;
    public List<String> llumins;
    public List<String> paper;
    public Random rnd;
    public boolean tancar;

    public Estanc(List<String> tabac, List<String> llumins, List<String> paper) {
        this.tabac = tabac;
        this.llumins = llumins;
        this.paper = paper;
        this.rnd = new Random();
        this.tancar = false;
    }

    public synchronized void addTabac() {
        tabac.add("tabac");
        System.out.println("Afegint tabac");
        notifyAll();
    }

    public synchronized void addLlumi() {
        llumins.add("llumi");
        System.out.println("Afegint llumi");
        notifyAll();
    }   

    public synchronized void addPaper() {
        paper.add("paper");
        System.out.println("Afegint paper");
        notifyAll();
    }

    public void nouSubministrament() {
        //33% de probabilitat cada uno y solo se hace uno 
        int opcio = rnd.nextInt(3); 
        if (opcio == 0)  {
            addTabac();
        }
        else if (opcio == 1) {
            addLlumi();

        } else {
            addPaper();
        }
    }

    public synchronized String venTabac() throws InterruptedException {
        while (tabac.isEmpty()) {
            wait();
        }
        return tabac.remove(0);
    }

    public synchronized String venLlumi() throws InterruptedException {
        while (llumins.isEmpty()) {
            wait();
        }
        return llumins.remove(0);
    }

    public synchronized String venPaper() throws InterruptedException {
        while (paper.isEmpty()) {
            wait();
        }
        return paper.remove(0);
    }

    public synchronized void tancarEstanc() {
        tancar = true;
        notifyAll();
        System.out.println("Estanc tancat");
    }

    @Override
    public void run() {
        while(!tancar) {
            nouSubministrament();
            try {
                long esperar = 500 + (long)(rnd.nextDouble() * 1000);
                Thread.sleep(esperar);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}