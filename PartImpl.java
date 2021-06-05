import java.rmi.*;
import java.net.*;
import java.rmi.server.*;
import java.rmi.registry.*;

public class PartImpl extends UnicastRemoteObject implements Part {
    
    public PartImpl() throws RemoteException{
        super();
    }

    public String sayHello() {
        return "HelloWorld!";
    }
}