public class Filosof extends Thread {
    private Forquilla forquillaEsquerra;
    private Forquilla forquillaDreta;

    public Filosof(String nom, Forquilla esquerra, Forquilla dreta) {
        super(nom);
        this.forquillaEsquerra = esquerra;
        this.forquillaDreta = dreta;
    }

    public void agafarForquilles() throws InterruptedException {
        forquillaEsquerra.agafar();
        forquillaDreta.agafar();
    }

    public void agafarForquillaEsquerra() throws InterruptedException {
        forquillaEsquerra.agafar();
    }

    public void agafarForquillaDreta() throws InterruptedException {
        forquillaDreta.agafar();
    }

    public void dixarForquilles() {
        forquillaDreta.deixar();
        forquillaEsquerra.deixar();
    }

    public void menjar() throws InterruptedException {
        System.out.println("Filosof: " + getName() + " agafa la forquilla esquerra " + forquillaEsquerra.getNum_forquilles());
        forquillaEsquerra.agafar();
        
        System.out.println("Filosof: " + getName() + " agafa la forquilla dreta " + forquillaDreta.getNum_forquilles());
        forquillaDreta.agafar();
        
        System.out.println("Filosof: " + getName() + " menja");
        
        long tempsMenjar = 1000 + (long)(Math.random() * 1000);
        Thread.sleep(tempsMenjar);
        
        forquillaDreta.deixar();
        System.out.println("Filosof: " + getName() + " deixa la dreta(" + forquillaDreta.getNum_forquilles() + ") i espera");
        
        forquillaEsquerra.deixar();
        System.out.println("Filosof: " + getName() + " deixa l'esquerra(" + forquillaEsquerra.getNum_forquilles() + ") i espera");
    }

    public void pensar() throws InterruptedException {
        System.out.println("Filosof: " + getName() + " pensant");
        long temps = 1000 + (long)(Math.random() * 1000);
        Thread.sleep(temps);
    }

    @Override
    public void run() {
        try {
            while (true) {
                pensar();
                menjar();
            }
        } catch (InterruptedException e) {
            System.out.println("Filosof: " + getName() + " ha acabat de menjar");
            System.out.println("Filosof: " + getName() + " pensant");
        }
    }
}