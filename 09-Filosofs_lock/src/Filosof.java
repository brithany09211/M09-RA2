public class Filosof extends Thread {
    private Forquilla forquillaEsquerra;
    private Forquilla forquillaDreta;

    private long iniciGana;
    private long fiGana;
    private long Gana;

    public Filosof(String nom, Forquilla esquerra, Forquilla dreta) {
        super(nom);
        this.forquillaEsquerra = esquerra;
        this.forquillaDreta = dreta;
        this.Gana = 0;
    }

    public void resetGana() {
        iniciGana = System.currentTimeMillis();
        Gana = 0;
    }

    public long calcularGana() {
        fiGana = System.currentTimeMillis();
        Gana = (fiGana - iniciGana) / 1000;
        return Gana;
    }

    public void agafarForquillaEsquerra() {
        forquillaEsquerra.agafar();
    }

    public void agafarForquillaDreta() {
        forquillaDreta.agafar();
    }

    public void agafarForquilles() {
        agafarForquillaEsquerra();
        agafarForquillaDreta();
    }

    public void dixarForquilles() {
        forquillaDreta.deixar();
        forquillaEsquerra.deixar();
    }

    public void menjar() throws InterruptedException {
        agafarForquilles();
        System.out.println(getName() + " té forquilles esq("
            + forquillaEsquerra.getNum() + ") dreta(" + forquillaDreta.getNum() + ")");

        long gana = calcularGana();
        System.out.println(getName() + " menja amb gana " + gana);

        long tempsMenjar = 1000 + (long)(Math.random() * 1000);
        Thread.sleep(tempsMenjar);

        System.out.println(getName() + " ha acabat de menjar");
        System.out.println(getName() + " deixa les forquilles");
        dixarForquilles();
    }

    public void pensar() throws InterruptedException {
        // resetGana inicia el comptador
        resetGana();
        System.out.println(getName() + " pensant");
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
            Thread.currentThread().interrupt();
        }
    }
}