
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class PartRepositoryImpl extends UnicastRemoteObject implements PartRepository {
    public String nome;
    public List<Part> parts;


    public PartRepositoryImpl(String nome, List<Part> parts) throws RemoteException{
        this.parts = parts;
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public int numParts() {
        return this.parts.size();
    }

    public void listar() throws RemoteException{
        for(int i = 0; i < parts.size(); i++){
            System.out.println(parts.get(i).getNome());
        }
    }  

    public Part getPart(int id) throws RemoteException{
        for(int i = 0; i < parts.size(); i++){
            if(id == parts.get(i).getId()){
                return parts.get(i);
            }
        }
        System.out.println("part não encontrada");
        return null;
    }

    @Override
    public PartImpl createPart(int id, String nome, String descricao, List<Part> subParts, String partRepository) throws RemoteException {
        PartImpl newPart = new PartImpl(nome, descricao, subParts, partRepository);
        return newPart;
    }
}