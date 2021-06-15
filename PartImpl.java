import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class PartImpl extends UnicastRemoteObject implements Part {
    public static int idCount = 0;
    
    public int id;
    public String nome;
    public String descricao;
    public List<Part> subParts;
    public String partRepository;
    
    public PartImpl(String nome, String descricao, List<Part> subParts, String partRepository) throws RemoteException{
        this.id = ++PartImpl.idCount;
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

    public String getPartRepository(){
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