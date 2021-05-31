import java.net.*;
import java.rmi.*;

public interface PartRepository extends Remote {
    String sayHello() throws RemoteException;
}