import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class PartImpl extends UnicastRemoteObject implements Part {
    private int id;
    private String nome;
    private String descricao;
    private List<Part> subParts;
    private String partRepositoryName;
    
    public PartImpl(int id, String nome, String descricao, List<Part> subParts, String partRepositoryName) throws RemoteException{
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.subParts = subParts;
        this.partRepositoryName = partRepositoryName;
    }

    public PartImpl(int id, String nome, String descricao, String partRepositoryName) throws RemoteException{
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.subParts = new ArrayList<Part>();
        this.partRepositoryName = partRepositoryName;
    }
    
    public String getNome(){
        return nome;
    }

    public int getId(){
        return id;
    }

    public String getDescricao(){
        return descricao;
    }

    public String getPartRepositoryName(){
        return partRepositoryName;
    }

    public boolean ehPrimitiva(){
        return subParts.size() == 0;
    }
    public int tamanho(){
        return subParts.size();
    }

    public void listar(){
        if(ehPrimitiva()){
            System.out.println("Vazio.");
            return;
        }  

        for(int i = 0; i < subParts.size(); i++){
            System.out.println(subParts.get(i));
        }
    }

    public void esvaziarSubParts(){
        subParts.clear();
    }

    public void addSubParts(List<Part> subParts){
        this.subParts = subParts;
    }
}