import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class PartRepositoryImpl extends UnicastRemoteObject implements PartRepository {
    public String nome;
    public List<Part> parts = new ArrayList<Part>();


    public PartRepositoryImpl(String nome) throws RemoteException{
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
        System.out.println("Part não encontrada");
        return null;
    }

    public void addPart(Part part, List<Part> subParts) throws RemoteException{
        part.addSubParts(subParts);
        parts.add(part);
    }
    
    @Override
    public PartImpl createPart(int id, String nome, String descricao, List<Part> subParts, String partRepository) throws RemoteException {
        PartImpl newPart = new PartImpl(id, nome, descricao, subParts, partRepository);
        return newPart;
    }
}