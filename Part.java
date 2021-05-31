import java.net.*;
import java.rmi.*;

public interface Part extends Remote {
    String sayHello() throws RemoteException;
}