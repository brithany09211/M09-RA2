public class Forquilla {
    private Boolean enUs = false;
    private int num_forquilles;
    private int num_propietari;
    private final int LLIURE = -1;

    public Forquilla(int num_forquilles) {
        this.num_forquilles = num_forquilles;
        this.num_propietari = LLIURE;
    }

    public int getNum_forquilles() {
        return num_forquilles;
    }

    public int getNum_propietari() {
        return num_propietari;
    }

    public void setNum_propietari(int num_propietari) {
        this.num_propietari = num_propietari;
    }

    public int getLLIURE() {
        return LLIURE;
    }
    
        public synchronized void agafar() throws InterruptedException {
        while (enUs) {
            wait();
        }
        enUs = true;
    }

    public synchronized void deixar() {
        enUs = false;
        notifyAll();
    }
}