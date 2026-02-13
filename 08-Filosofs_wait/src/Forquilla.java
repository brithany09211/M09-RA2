public class Forquilla {
    Boolean enUs = false;
    int num_forquilles;
    int num_propietari;
    final int LLIURE = -1; 

    public Forquilla (int num_forquilles) {
        this.num_forquilles = num_forquilles;
    }

    public Boolean isEnUs() {
        return enUs;
    }

    public void setEnUs(Boolean enUs) {
        this.enUs = enUs;
    }

    public int getNum_forquilles() {
        return num_forquilles;
    }

    public void setNum_forquilles(int num_forquilles) {
        this.num_forquilles = num_forquilles;
    }

    public boolean agafar() {
        if (!enUs) {
            enUs = true;
            return true;
        }
        return false;
    }

    public void deixar() {
        enUs = false;
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
    public void esperarLliure() throws InterruptedException {
        while (enUs) {
            wait();
        }
    }
    
}