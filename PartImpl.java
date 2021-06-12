import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class PartImpl extends UnicastRemoteObject implements Part {
    public int id;
    public String nome;
    public String descricao;
    public List<Part> subParts;
    public PartRepository partRepository;
    
    public PartImpl(int id, String nome, String descricao, List<Part> subParts, PartRepository partRepository) throws RemoteException{
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.subParts = subParts;
        this.partRepository = partRepository;
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

    public PartRepository getPartRepository(){
        return partRepository;
    }

    public boolean ehPrimitiva(){
        return subParts.size() == 0;
    }
    public int tamanho(){
        return subParts.size();
    }

    public void listar(){
        if(ehPrimitiva()){
            System.out.println("Vazio!! :P :D :(");
            return;
        }  

        for(int i = 0; i < subParts.size(); i++){
            System.out.println(subParts.get(i));
        }
    }

    public String sayHello() {
        return "HelloWorld!";
    }
}