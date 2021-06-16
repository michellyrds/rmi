import java.rmi.*;
import java.util.List;

public interface Part extends Remote {
    String getNome() throws RemoteException;
    int getId() throws RemoteException;
    String getDescricao() throws RemoteException;
    String getPartRepository() throws RemoteException;
    boolean ehPrimitiva() throws RemoteException; 
    int tamanho() throws RemoteException;
    void listar() throws RemoteException;
    void esvaziarSubParts() throws RemoteException;
    void addSubParts(List<Part> subParts) throws RemoteException;
    String sayHello() throws RemoteException;
}