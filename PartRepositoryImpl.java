import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PartRepositoryImpl extends UnicastRemoteObject implements PartRepository {
    
    public PartRepositoryImpl() throws RemoteException{
        super();
    }

    public String sayHello() {
        return "HelloWorld!";
    }
}