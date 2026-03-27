public class Home extends Thread {
    private String nom;
    private int lavabo;
    private BanyUnisex bany;

    public Home (String nom, BanyUnisex bany) { 
        this.nom = nom;
        this.bany = bany;
    }

    @Override
    public void run() {
        try {
            BanyUnisex.entraHome();
            BanyUnisex.utilitzaLavabo();
            BanyUnisex.surtHome();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}