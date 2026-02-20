public class Taula {
    private Filosof[] comensals;
    private Forquilla[] forquilles;

    public Taula(int numFilosofs) {
        comensals = new Filosof[numFilosofs];
        forquilles = new Forquilla[numFilosofs];

        for (int i = 0; i < numFilosofs; i++) {
            forquilles[i] = new Forquilla(i);
        }

        for (int j = 0; j < numFilosofs; j++) {
            String nom = "Fil" + j;
            Forquilla esquerra = forquilles[j];
            Forquilla dreta = forquilles[(j + 1) % numFilosofs];
            comensals[j] = new Filosof(nom, esquerra, dreta);
        }
    }

    public void showTaula() {
        for (int i = 0; i < comensals.length; i++) {
            System.out.println("Comensal:" + comensals[i].getName()
                + " esq:" + i
                + " dret:" + ((i + 1) % comensals.length));
        }
        System.out.println("----------------------------");
    }

    public void cridarATaula() {
        for (Filosof f : comensals) {
            f.start();
        }
    }

    public static void main(String[] args) {
        Taula taula = new Taula(4);
        taula.showTaula();
        taula.cridarATaula();
    }
}