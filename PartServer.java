import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class PartServer {
    public PartRepository partRepository;
    public static final String host = "localhost";

    public PartServer(String serverName, String partRepositoryName, int port){
        try{
            partRepository = new PartRepositoryImpl(partRepositoryName);
            Registry registry;
            registry = LocateRegistry.createRegistry(port);
            registry.rebind(serverName, partRepository);
            System.out.println(partRepositoryName + " Server pronto.");
        } catch (Exception e){
            System.out.println("PartServer erro "+ e.getMessage());
            System.exit(0);
        }
    }

    // public static void main (String args[]) {
    //     //Cria e instala o security manager
    // //System.setSecurityManager(new RMISecurityManager() );

    //     try {
    //         PartRepository obj = new PartRepositoryImpl("animalesco");
    //        //Registry registry = LocateRegistry.createRegistry(port); // subindo um server na porta passada como parametro
    //         registry.rebind("PartServer", obj); //precisamos arrumar o bind
    //         System.out.println("Part Server pronto.");
    //     } catch(Exception e) {
    //         System.out.println("PartServer erro "+ e.getMessage());
    //         System.exit(0);
    //     }
    // }
}