import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class PartRepositoryImpl extends UnicastRemoteObject implements PartRepository {
    public static int idCount = 0;
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
            System.out.print(parts.get(i).getNome()+" ");
            System.out.println(parts.get(i).getId());
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
        PartImpl newPart = new PartImpl(++PartRepositoryImpl.idCount, part.getNome(), part.getDescricao(), subParts, this.nome);
        //part.addSubParts(subParts);
        this.parts.add(newPart);
    }
    
    public int createPart(String nome, String descricao, List<Part> subParts) throws RemoteException {
        PartImpl newPart = new PartImpl(++PartRepositoryImpl.idCount, nome, descricao, subParts, this.nome);
        parts.add(newPart);
        return newPart.getId();
    }
}