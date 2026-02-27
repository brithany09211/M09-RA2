public class Barri {
    public Estanc estanc;
    public Fumador fumadors[] = new Fumador[3];

    public Barri() {
        this.estanc = new Estanc();
        for (int i = 0; i < 3; i++) {
            fumadors[i] = new Fumador(estanc, i);
        }
    }

    
}
