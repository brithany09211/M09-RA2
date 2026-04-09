public class Home extends Thread {
    private String nom;
    private BanyUnisex lavabo;

    public Home (String nom, BanyUnisex lavabo) { 
        this.nom = nom;
        this.lavabo = lavabo;
    }

    @Override
    public void run() {
        try {
            System.out.println(nom + " vol entrar al bany");
            lavabo.entraHome();

            Thread.sleep(1000 + (long)(Math.random() * 1000));
            lavabo.surtHome();
            System.out.println(nom + " ha acabat d'usar el bany");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}