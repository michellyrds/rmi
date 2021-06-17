import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class PartClient{
    public static final String host = "localhost";

    static Part part;
    static PartRepository obj;
    static List<Part> subParts = new ArrayList<Part>();

    public static void main(String args[]){
    
        
        try {
            //Part obj = (Part)Naming.lookup("rmi://localhost/PartServer"); //nomedamaquina que fica no etc/hosts (ubuntu)
            //System.out.println(hv.verify(obj, )); //erro: precisamos configurar a ssl session
            Registry registry = LocateRegistry.getRegistry(host);
            PartRepository obj = (PartRepository) registry.lookup("PartServer");
            System.out.println("PartServer: " + obj.getNome());

            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("Digite um comando do sistema:");
                String comando = sc.nextLine(); 
                
                try {
                    if(comando.isEmpty()){
                        System.out.println("Digite um comando do sistema. Para saber os comandos do sistema, digite \"help\"");
                    }
                    
                    else if (comando.equals("bind")) {
                        System.out.println("Digite qual repositório deseja entrar:");
                        registry = LocateRegistry.getRegistry(sc.nextLine());
                        obj = (PartRepository) registry.lookup("PartServer");
                    }

                    else if (comando.equals("listp")) {
                        obj.listar();
                    } 

                    else if (comando.equals("createp")) {
                        System.out.println("Digite o id da peça que deseja criar:");
                        String nome = sc.nextString();
                        System.out.println("Digite a descrição da peça que deseja criar:"); 
                        String descricao = sc.nextString();
                        part = obj.createPart(nome, descricao);
                    } 
                    
                    else if (comando.equals("getp")) {
                        System.out.println("Digite o id da peça que deseja buscar:");
                        int id = sc.nextInt();
                        part = obj.getPart(id);
                        System.out.println("Voce agora esta com a part " + part.getNome());
                    } 

                    else if (comando.equals("showp")) {
                        System.out.println("Voce agora esta com a part " + part.getNome());    
                        System.out.println("ID:" + part.getId());
                        System.out.println("Descricao: " + part.getDescricao());
                        System.out.println("Repositório: " + part.getPartRepository());
                        System.out.println("Eh primitiva: " + part.ehPrimitiva());
                        System.out.println("Tamanho: " + part.tamanho());
                    } 
                    
                    else if (comando.equals("clearlist")) { 
                        part.esvaziarSubParts();    
                        System.out.println("Lista de sub-peças corrente esvaziada");
                    }
                    
                    else if (comando.equals("addsubpart")) {
                        subParts.add(part);
                    }
                
                    else if (comando.equals("addp")) { 
                        obj.addPart(part, subParts);
                    }
                    
                    else if (comando.equals("help")) {
                        System.out.println("Lista de comandos:");
                        System.out.println("bind - Faz o cliente se conectar a outro servidor e muda o repositório corrente.");
                        System.out.println("listp - Lista as peças do repositório corrente.");
                        System.out.println("getp - Busca uma peça por código.");
                        System.out.println("showp - Mostra atributos da peça corrente");
                        System.out.println("clearlist - Esvazia a lista de sub-peças corrente.");
                        System.out.println("addsubpart - Adiciona à lista de sub-peças corrente n unidades da peça corrente.");
                        System.out.println("addp - Adiciona uma peça ao repositório corrente.");
                        System.out.println("quit - Encerra a execução do cliente.");
                    }

                    else if (comando.equals("quit")) {
                        System.out.println("Saindo do progrma.");
;                       System.exit(0);
                    }
                    
                    else { 
                        System.out.println("Comando inválido. Digite \"help\" para obter ajuda.");
                    }
                        
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }

        } catch(Exception e) {
            System.out.println("PartClient error "+ e.getMessage());
        }
        System.exit(0);
    }
}