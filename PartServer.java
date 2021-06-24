import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

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

    public static void main (String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome do servidor:");
        String serverName = sc.nextLine();
        System.out.println("Digite o nome do repositório:");
        String repositoryName = sc.nextLine();
        System.out.println("Digite a porta do servidor:");
        int port = sc.nextInt();
        
        PartServer newServer = new PartServer(serverName, repositoryName, port);

        System.out.println("Não esqueça de adiconar o nome do servidor e a porta no createServerList do PartClient!");
    }
}