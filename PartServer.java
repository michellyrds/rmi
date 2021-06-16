import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class PartServer {
    public static final String host = "localhost";
    public static final int port = 1099; // porta default


    public static void main (String args[]) {
        //Cria e instala o security manager
    //System.setSecurityManager(new RMISecurityManager() );

        try {
            Part obj = new PartImpl(1, "teste", "esse é um teste", null, "Olá");
            Registry registry = LocateRegistry.createRegistry(port); // subindo um server na porta passada como parametro
            registry.rebind("PartServer", obj); //precisamos arrumar o bind
            System.out.println("Part Server pronto.");
        } catch(Exception e) {
            System.out.println("PartServer erro "+ e.getMessage());
            System.exit(0);
        }
    }
}