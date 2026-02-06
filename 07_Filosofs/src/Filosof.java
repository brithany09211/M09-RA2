public class Filosof extends Thread {
    private String nom;
    private Forquilla forquillaEsquerra;
    private Forquilla forquillaDreta;
    private int gana = 0;

    public Filosof(String nom, Forquilla esquerra, Forquilla dreta) {
        this.nom = nom;
        this.forquillaEsquerra = esquerra;
        this.forquillaDreta = dreta;
    }
    @Override
    public void run() {
        try {
            while (true) {
                //pensar();
                //menjar();
            }
        } catch (InterruptedException e) {
            System.out.println("Filosof: " + nom + " ha acabat de menjar");
            System.out.println("Filosof: " + nom + " pensant");
        }
    }
    //contador de la gana
    //metodo mejar y pensar
    //metodo de ejecucion
}
