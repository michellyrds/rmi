import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.net.*;

public class PartRepositoryImpl extends UnicastRemoteObject implements PartRepository {
    
    public PartRepositoryImpl() throws RemoteException{
        super();
    }

    public String sayHello() {
        return "HelloWorld!";
    }
}