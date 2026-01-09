public class Futbolista extends Thread {
    private int ngols;
    private int ntirades;

    public static final int NUM_JUGADORS = 11;
    public static final int NUM_TIRADES = 20;
    public static final double PROBABILITAT = 0.5;

    public Futbolista (String nom) {
        super(nom); 
        this.ngols = 0;
        this.ntirades = 0;
    }

    public int getNgols() {
        return ngols;
    }

    public int getNtirades() {
        return ntirades;
    }

    @Override
    public void run() {
        for (int i = 0; i < NUM_TIRADES; i++) {
            ntirades++;

            if(Math.random() < PROBABILITAT) { //Si es menor a 0.5 es gol si no es false
                ngols++;
            }
        }
    }

    public static void main(String[] args) {
        String[] noms = {"Messi", "Vinicius", "Torres", "Ramos", "Ronaldo", "Lewan", "Belli", "Arnau", "Aspas", "Bellingham", "MBapé"};

        Futbolista [] jugadors = new Futbolista[NUM_JUGADORS];

        System.out.println("Inici dels xuts --------------------");

        for (int i = 0; i < NUM_JUGADORS; i++) {
            jugadors[i] = new Futbolista(noms[i]);
            jugadors[i].start();
        }

        for (int i = 0; i < NUM_JUGADORS; i++) {
            try {
                jugadors[i].join(); 

            } catch (Exception e) {
                System.out.println(e);
            }
        }
        System.out.println("Fi dels xuts ----------------------");
        
        System.out.println("--- Estadístiques ------");
        for (int i = 0; i < Futbolista.NUM_JUGADORS; i++) {
            System.out.println(jugadors[i].getName() + "     => " + jugadors[i].getNgols() + " gols");
        }
    }
}

