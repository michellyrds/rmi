import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;


public class PartClient{
    private String host = "localhost"; //host default

    private Part part;
    private List<Part> subParts = new ArrayList<Part>();
    static PartRepository repositorioCorrente;
    private Map<String, Integer> servers;
    Registry registry;

    public PartClient() throws RemoteException{
        this.registry = LocateRegistry.getRegistry(host);
    } //construtor default

    public PartClient(String host) throws RemoteException{ //especifica o host do client
        this.host = host;
        this.registry = LocateRegistry.getRegistry(host);
    }

    public String searchServer(String repositoryName){
        if (repositoryName.equals("repoAle")){
            return "serverAle";
        } else if (repositoryName.equals("repoMi")){
            return "serverMi";
        } else if (repositoryName.equals("repoSena")){
            return "serverSena";
        } else if (repositoryName.equals("repoYumi")){
            return "serverYumi";
        } else {
            return "Este servidor não existe";
        }
    }
    
    public void bind(String repositoryName) throws RemoteException, NotBoundException{
        try{
            String serverName = searchServer(repositoryName);
            int port = servers.get(serverName);
            registry = LocateRegistry.getRegistry(port);
            repositorioCorrente = (PartRepository) registry.lookup(serverName);
            System.out.println("Conectado ao " + repositorioCorrente.getNome() + " no servidor " + serverName);
        } catch(Exception e){
            System.out.println("Conexão ao repositório "+ repositoryName + " falhou.");
        }
    }

    public void run() throws RemoteException{
        Map<String, Integer> serverPortas = new HashMap<String, Integer>();
        serverPortas.put("serverAle", 1099);
        serverPortas.put("serverMi", 53903);
        serverPortas.put("serverSena", 53907);
        serverPortas.put("serverYumi", 53906);
         
        try {
            // PartRepository repositorioCorrente = (PartRepository) registry.lookup(partRepositoryNameDefault);
            // System.out.println("repositorio do ale: " + repositorioCorrente.getNome());

            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("Digite um comando do sistema. ");
                String comando = sc.nextLine(); 
                
                try {
                    if(comando.isEmpty()){
                        System.out.println("Digite um comando do sistema. Para saber os comandos do sistema, digite \"help\"");
                    }
                    
                    else if (comando.equals("bind")) {
                        System.out.println("Digite o nome do repositório que deseja conectar:");
                        String nome = sc.nextLine();
                        String serverName = searchServer(nome);
                        int porta = serverPortas.get(serverName);
                        registry = LocateRegistry.getRegistry(porta);
                        repositorioCorrente = (PartRepository) registry.lookup(serverName);
                        System.out.println("Conectado ao " + repositorioCorrente.getNome() + " no servidor " + serverName);
                    }

                    else if (comando.equals("listp")) {
                        repositorioCorrente.listar();
                    } 

                    else if (comando.equals("createp")) {
                        System.out.println("Digite o nome da peça que deseja criar:"); 
                        String nome = sc.nextLine();
                        System.out.println("Digite a descrição da peça que deseja criar:"); 
                        String descricao = sc.nextLine();
                        List<Part> subPartsNulas = new ArrayList<Part>();
                        int idCurrentPart = repositorioCorrente.createPart(nome, descricao, subPartsNulas);
                        part = repositorioCorrente.getPart(idCurrentPart);
                    } 
                    
                    else if (comando.equals("getp")) {
                        System.out.println("Digite o id da peça que deseja buscar:");
                        int id = sc.nextInt();
                        part = repositorioCorrente.getPart(id);
                        System.out.println("Voce agora esta com a part " + part.getNome());
                    } 

                    else if (comando.equals("showp")) {
                        System.out.println("Voce agora esta com a part " + part.getNome());    
                        System.out.println("ID:" + part.getId());
                        System.out.println("Descricao: " + part.getDescricao());
                        System.out.println("Repositório: " + part.getPartRepositoryName());
                        System.out.println("Eh primitiva: " + part.ehPrimitiva());
                        System.out.println("Tamanho: " + part.tamanho());
                    } 
                    
                    else if (comando.equals("showsubps")) {
                        for(int i = 0; i<subParts.size(); i++){
                            System.out.println(subParts.get(i).getNome());
                        }
                    }

                    else if (comando.equals("clearlist")) { 
                        subParts = new ArrayList<Part>(); 
                        System.out.println("Lista de sub-peças corrente esvaziada");
                    }
                    
                    else if (comando.equals("addsubpart")) {
                        if(part == null){
                            System.out.println("Nenhuma peça corrente selecionada.");
                        }else{
                            System.out.println("Digite o número de unidades da peça corrente a ser inserida:");
                            int n = sc.nextInt();
                            for(int i = 0; i < n; i++){
                                subParts.add(part);
                            }
                        }
                    }
                
                    else if (comando.equals("addp")) { 
                        repositorioCorrente.addPart(part, subParts);
                    }
                    
                    else if (comando.equals("help")) {
                        System.out.println("Lista de comandos:");
                        System.out.println("bind - Faz o cliente se conectar a outro servidor e muda o repositório corrente.");
                        System.out.println("listp - Lista as peças do repositório corrente.");
                        System.out.println("getp - Busca uma peça por código.");
                        System.out.println("showp - Mostra atributos da peça corrente");
                        System.out.println("clearlist - Esvazia a lista de sub-peças corrente.");
                        System.out.println("createp - Cria uma peça e adiciona ao repositório corrente");
                        System.out.println("addsubpart - Adiciona à lista de sub-peças corrente n unidades da peça corrente.");
                        System.out.println("addp - Adiciona uma peça ao repositório corrente.");
                        System.out.println("quit - Encerra a execução do cliente.");
                        System.out.println("\nLista de repositorios:");
                        System.out.println("repoAle");
                        System.out.println("repoMi");
                        System.out.println("repoSena");
                        System.out.println("repoYumi");
                    }

                    else if (comando.equals("quit")) {
                        System.out.println("Saindo do programa...");
                        System.exit(0);
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