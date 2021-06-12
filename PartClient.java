import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class PartClient{
    public static final String host = "localhost";
    public static void main(String args[]){

        try {
            //Part obj = (Part)Naming.lookup("rmi://localhost/PartServer"); //nomedamaquina que fica no etc/hosts (ubuntu)
            //System.out.println(hv.verify(obj, )); //erro: precisamos configurar a ssl session
            Registry registry = LocateRegistry.getRegistry(host);
            Part obj = (Part) registry.lookup("PartServer");
            System.out.println("PartClient: " + obj.sayHello());
        } catch(Exception e) {
            System.out.println("PartClient error "+ e.getMessage());
        }
        System.exit(0);
    }
}