public class Fumador extends Thread {
    public Estanc estanc;
    public int id;
    public Tabac tabac;
    public Llumi llumi;
    public Paper paper;
    public int num_fumades;

    public Fumador(Estanc estanc, int id) {
        this.estanc = estanc;
        this.id = id;
    }
    
    public void fuma() throws InterruptedException {
        tabac = null;
        llumi = null;
        paper = null;
        num_fumades++;
        System.out.println("Fumador " + id + "fumant");
        long esperar = 500 + (long)(Math.random() * 500);
        Thread.sleep(esperar);
        System.out.println("Fumador " + id + "ha fumat " + num_fumades + " vegades");
    }

    public void compraTabac() throws InterruptedException {
        tabac = estanc.venTabac();
    }

    public void compraPaper() throws InterruptedException {
        paper = estanc.venPaper();
    }

    public void compraLlumi() throws InterruptedException {
        llumi = estanc.venLlumi();
    }


    @Override
    public void run() {
        int fumada = 0;
        while(fumada < 3) {
            try {
                compraTabac();
                compraPaper();
                compraLlumi();
                fuma();
                fumada++;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
