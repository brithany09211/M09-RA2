public class Motor extends Thread {
    private int potencia = 0;
    private int objectiu = 0;
    private boolean actiu = false;

    public Motor(String nom) {
        super(nom);
    }

    public synchronized void setPotencia(int p) {
        this.objectiu = p;
        this.actiu = true; 
    }

    

    public int getPotencia() {
        return potencia;
    }

    public int getObjectiu() {
        return objectiu;
    }

    public boolean isActiu() {
        return actiu;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (actiu) {
                    String accio;
                    if (potencia < objectiu) {
                        potencia++;
                        accio = "Incre.";
                    } else if (potencia > objectiu) {
                        potencia--;
                        accio = "Decre.";
                    } else {
                        accio = "FerRes";
                        actiu = false; 
                    }

                    System.out.println(getName() + ": " + accio + " Objectiu: " + objectiu + " Actual: " + potencia);

                    Thread.sleep(800 + (int)(Math.random() * 700));
                } else {
                    Thread.sleep(100); 
                }
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}