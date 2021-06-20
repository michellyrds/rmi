import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class PartRepositoryImpl extends UnicastRemoteObject implements PartRepository {
    private static int idCount = 0; //auto-incremento dos ID das peças do repositório
    private String nome;
    private List<Part> parts;


    public PartRepositoryImpl(String nome) throws RemoteException{
        this.nome = nome;
        this.parts = new ArrayList<Part>();
    }

    private int genId(){ //Gera o ID das peças
        return ++idCount;
    }

    public String getNome() {
        return this.nome;
    }

    public int numParts() {
        return this.parts.size();
    }

    public void listar() throws RemoteException{
        if(parts.size() == 0){
            System.out.println("Repositório vazio");
        } else {
            for(int i = 0; i < parts.size(); i++){
                System.out.print(parts.get(i).getNome()+" ");
                System.out.println(parts.get(i).getId());
            }
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
        PartImpl newPart = new PartImpl(genId(), part.getNome(), part.getDescricao(), subParts, this.nome);
        //part.addSubParts(subParts);
        this.parts.add(newPart);
    }
    
    public int createPart(String nome, String descricao, List<Part> subParts) throws RemoteException {
        PartImpl newPart = new PartImpl(genId(), nome, descricao, subParts, this.nome);
        parts.add(newPart);
        return newPart.getId();
    }
}