import java.util.Random;

public class DormAleatori extends Thread {
    private static int interval_aleatori = 1000;
    private long tempsConstructor;
    
    public DormAleatori(String nom) {
        super(nom);
        this.tempsConstructor = System.currentTimeMillis();
    }
    
    @Override
    public void run() {
        Random random = new Random();
        
        for (int i = 0; i < 10; i++) {
            long tempsTotal = System.currentTimeMillis() - tempsConstructor;
            int tempsDormir = random.nextInt(interval_aleatori);
            
            System.out.println(getName() + "(" + i + ") a dormir  " + 
                             tempsDormir + "ms total  " + tempsTotal + "ms");
            
            try {
                Thread.sleep(tempsDormir);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        System.out.println("-- Fi de main -----------");
        
        DormAleatori joan = new DormAleatori("Joan");
        DormAleatori pep = new DormAleatori("Pep");
        
        joan.start();
        pep.start();
        
        try {
            joan.join();
            pep.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
