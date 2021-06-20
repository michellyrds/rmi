import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class PartServer {
    public PartRepository partRepository;
    private String serverName;
    private String partRepositoryName;
    private int port;

    public static final String host = "localhost";

    public PartServer(String serverName, String partRepositoryName, int port){
        try{
            partRepository = new PartRepositoryImpl(partRepositoryName);
            Registry registry;
            registry = LocateRegistry.createRegistry(port);
            registry.rebind(serverName, partRepository);
            System.out.println(serverName + " Server pronto.");
        } catch (Exception e){
            System.err.println(serverName + " erro "+ e.getMessage());
            System.exit(0);
        }

        this.partRepositoryName = partRepositoryName;
        this.serverName = serverName;
        this.port = port;
    }

    public String getServerName(){
        return this.serverName;
    }

    public String getPartRepositoryName(){
        return this.partRepositoryName;
    }

    public int getPort(){
        return this.port;
    }
}