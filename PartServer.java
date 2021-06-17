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
}