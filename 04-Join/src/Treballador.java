import java.util.Random;

public class Treballador extends Thread {
    private float sou_anual_brut;
    private int edat_inici_treball;
    private int edat_fi_treball;
    private int edat_actual;
    private float cobrat;
    private Random rnd;

    public Treballador(String nom, float sou, int inici, int fi) {
        super(nom); 
        this.sou_anual_brut = sou;
        this.edat_inici_treball = inici;
        this.edat_fi_treball = fi;
        this.edat_actual = 0;
        this.cobrat = 0.0f;
        this.rnd = new Random();
    }

    public void cobra() {
        float souMensual = sou_anual_brut / 12.0f; //divide el sueldo bruto en 12 partes
        this.cobrat += souMensual;

        int delay = 1 + (int)(rnd.nextFloat() * 9);
        try {
            Thread.sleep(delay);  
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pagaImpostos() {
        float impostos = (sou_anual_brut / 12.0f) * 0.24f;
        this.cobrat -= impostos;

        int delay = 1 + (int)(rnd.nextFloat() * 9);
        try {
            Thread.sleep(delay);  
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        for (edat_actual = 0; edat_actual < edat_fi_treball; edat_actual++) {
            if (edat_actual >= edat_inici_treball) {
                for (int mes = 0; mes < 12; mes++) {
                    cobra();
                    pagaImpostos();
                }
            }
        }

        try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    public int getEdat() {
        return edat_actual;
    }

    public float getCobrat() {
        return cobrat;
    }
    
}
