import java.rmi.*;

public interface Part extends Remote {
    String getNome() throws RemoteException;
    int getId() throws RemoteException;
    String getDescricao() throws RemoteException;
    String getPartRepository() throws RemoteException;
    boolean ehPrimitiva() throws RemoteException; 
    int tamanho() throws RemoteException;
    void listar() throws RemoteException;
    String sayHello() throws RemoteException;
}