import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PartServer {
    public static final String host = "localhost";
    public static final int port = 39819;


    public static void main (String args[]) {
        //Cria e instala o security manager
    //System.setSecurityManager(new RMISecurityManager() );

//----------------------------------------------------------------------
        // try {
        //     // Este é o nome pelo qual o serviço
        //     // será posteriormente localizado
        //     String serviceName = "Count001";
        //     // Cria objeto servidor
        //     CountImpl myCount = new CountImpl();
        //     // Obtém referência para rmiregistry que
        //     // está executando na máquina local, operando
        //     // na porta default (1099)
        //     Registry r = LocateRegistry.getRegistry();
        //     // Registra a ligação nome-objeto servidor
        //     // no rmiregistry 
        //     r.bind(serviceName, myCount);
        //     // Tudo feito, servidor está preparado
        //     // para receber solicitações de clientes
        //     System.out.println("Count Server ready.");
        // } 
        // catch (Exception e) {
        //     System.out.println("Exception: " + e.getMessage());
        //     e.printStackTrace();
        // }

        //try{
        //  PartServer partServer = new PartServer(servidorNome); 
		//	Registry reg = LocateRegistry.createRegistry(porta);
		//	reg.bind("PartRepositoryServer", partServer);
        //  System.out.println("Part Server pronto.");
        //}
//----------------------------------------------------------------------

        try {
            PartImpl obj = new PartImpl();
            Registry registry = LocateRegistry.getRegistry(host, port);
            registry.rebind("PartServer", obj); //precisamos arrumar o bind
            System.out.println("Part Server pronto.");
        } catch(Exception e) {
            System.out.println("PartServer erro "+ e.getMessage());
            System.exit(0);
        }
    }
}