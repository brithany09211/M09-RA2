public class PrincipalIguals {
    public static void main(String[] args) {
        Fil filJuan = new Fil("Juan");
        Fil filPepe = new Fil("Pepe");

        // Establecer la misma prioridad (normal/5) para los dos 
        filJuan.setPriority(Thread.NORM_PRIORITY);
        filPepe.setPriority(Thread.NORM_PRIORITY);

        filPepe.start();
        filJuan.start();

        System.out.println("Acaba thread main");
    }
}