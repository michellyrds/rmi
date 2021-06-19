import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface PartRepository extends Remote {
    public String getNome() throws RemoteException;
    public int numParts() throws RemoteException;
    public void listar() throws RemoteException;
    public Part getPart(int id) throws RemoteException;
    public void addPart(Part part, List<Part> subParts) throws RemoteException;
    public void createPart(String nome, String descricao, List<Part> subParts) throws RemoteException;
}