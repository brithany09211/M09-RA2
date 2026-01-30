import java.util.ArrayList;
import java.util.List;

public class Esdeveniment {
    private int num_places;
    private List<Assistent> reservados;

    private Esdeveniment (int num_places_max) {
        this.num_places = num_places_max;
        this.reservados = new ArrayList<>();
    }

    public synchronized void ferReserva(Assistent a) {
        while (num_places == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        reservados.add(a);
        num_places--;
        System.out.println(a.getNom() + " ha fet una reserva. Places disponibles: " + num_places);
    }
    public synchronized void cancelaReserva(Assistent a) {
        if(reservados.contains(a)) {
            reservados.remove(a);
            num_places++;
            System.out.println(a.getNom() + " ha cancel·lat una reserva. Places disponibles: " + num_places);
            notifyAll();
        } else {
            System.out.println(a.getNom() + " no ha pogut cancel·lar una reserva inexistent. Places disponibles: " + num_places);
        }
    }
}
