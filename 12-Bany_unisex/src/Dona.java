public class Dona extends Thread {
    private String nom;
    private int lavabo;

    private BanyUnisex bany;

    public Dona (String nom, BanyUnisex bany) { 
        this.nom = nom;
        this.bany = bany;
    }

    @Override
    public void run() {
        try {
            BanyUnisex.entraDona();
            BanyUnisex.utilitzaLavabo();
            BanyUnisex.surtDona();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}