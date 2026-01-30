public class Assistent extends Thread {
    private Esdeveniment esdeveniment;
    private String nom;
    private Assistent (String nom, Esdeveniment esdeveniment) {
        this.nom = nom;
        this.esdeveniment = esdeveniment;
    }

    @Override
    public void run() {
        double probabilitat = Math.random();

        if(probabilitat < 0.5) {
            
            System.out.println("Assistent-");
        }
    }
    
    public String getNom() {
        return nom;
    }
    
}
