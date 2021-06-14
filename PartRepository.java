import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface PartRepository extends Remote {
    public String getNome() throws RemoteException;
    public int numParts() throws RemoteException;
    public void listar() throws RemoteException;
    public Part getPart(int id) throws RemoteException;
    public PartImpl createPart(int id, String nome, String descricao, List<Part> subParts, String partRepository) throws RemoteException;
}