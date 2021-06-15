import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class PartServer{
    PartRepositoryImpl partRepository = null;

    // public PartServer(String nome, List<Part> parts) throws RemoteException{
    //     PartRepositoryImpl newPartRepository = PartRepositoryImpl(nome, parts);
        
    // }


    public static final String host = "localhost";
    public static final int port = 1099; // porta default

    public boolean createServer(String host, int port){

        try{
            //PartImpl obj
        } catch(Exception e){

        }
        return true;
    }

    public static void main (String args[]) {
        //Cria e instala o security manager
    //System.setSecurityManager(new RMISecurityManager() );

        try {
            PartImpl obj = new PartImpl("teste", "esse é um teste", null, "Olá");
            Registry registry = LocateRegistry.createRegistry(port); // subindo um server na porta passada como parametro
            registry.rebind("PartServer", obj); //precisamos arrumar o bind
            System.out.println("Part Server pronto.");
        } catch(Exception e) {
            System.out.println("PartServer erro "+ e.getMessage());
            System.exit(0);
        }
    }
}