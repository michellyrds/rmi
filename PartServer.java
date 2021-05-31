import java.net.*;
import java.rmi.*;

public class PartServer {
    public static void main (String args[]) {
        //Cria e instala o security manager
    //System.setSecurityManager(new RMISecurityManager() );
        try {
            //Cria HelloImpl
            PartImpl obj = new PartImpl();
            Naming.rebind("PartServer", obj);
            System.out.println("Part Server pronto.");
        } catch(Exception e) {
            System.out.println("PartServer erro"+ e.getMessage());
            System.exit(0);
        }
    }
}