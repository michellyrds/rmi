import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PartImpl extends UnicastRemoteObject implements Part {
    
    public PartImpl() throws RemoteException{
        super();
    }

    public String sayHello() {
        return "HelloWorld!";
    }
}