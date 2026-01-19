import java.util.Scanner;

public class Coet {
    public static void passarAPotencia(int p, Motor[] motors) {
        if (p < 0 || p > 10) {
            System.out.println("Error: La potència ha d'estar entre 0 i 10");
            return;
        }
        
        System.out.println("Passant a potència " + p);
        for (Motor m : motors) {
            m.setPotencia(p);
        }
    }

    public static void main(String[] args) {
        Motor[] motors = new Motor[4];
        for (int i = 0; i < 4; i++) {
            motors[i] = new Motor("Motor " + i);
            motors[i].start();
        }

        Scanner sc = new Scanner(System.in);
        
        while (true) {
            if (sc.hasNextInt()) {
                int p = sc.nextInt();
                passarAPotencia(p, motors);
                
                if (p == 0) {
                    // Espera a que tots els motors arribin a 0
                    boolean totsCero = false;
                    while (!totsCero) {
                        try {
                            Thread.sleep(100);
                            totsCero = true;
                            for (Motor m : motors) {
                                if (m.getPotencia() != 0) {
                                    totsCero = false;
                                    break;
                                }
                            }
                        } catch (InterruptedException e) {
                            break;
                        }
                    }
                    break;
                }
            }
        }
        
        sc.close();
        System.out.println("0");
        System.exit(0);
    }
}