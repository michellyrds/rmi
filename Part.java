import java.rmi.*;

public interface Part extends Remote {
    String getNome();
    int getId();
    String getDescricao();
    PartRepository getPartRepository();
    boolean ehPrimitiva(); 
    int tamanho();
    void listar();
    String sayHello() throws RemoteException;
}