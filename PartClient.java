import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class PartClient {
    private String host = "localhost"; // host default

    private Part currentPart = null;
    private List<Map<Part, Integer>> subParts = new ArrayList<Map<Part, Integer>>();
    static PartRepository repositorioCorrente;
    private Map<String, Integer> servers; // Map<serverName, port>
    Registry registry;

    public PartClient() throws RemoteException {
        this.registry = LocateRegistry.getRegistry(host);
        createServerList();
    } // construtor default

    public PartClient(String host) throws RemoteException { // especifica o host do client
        this.host = host;
        this.registry = LocateRegistry.getRegistry(host);
        createServerList();
    }

    private void createServerList() { // preenche a lista dos servidores disponíveis (hardcoded)
        servers = new HashMap<String, Integer>();

        servers.put("serverAle", 1099);
        servers.put("serverMi", 53903);
        servers.put("serverSena", 53907);
        servers.put("serverYumi", 53906);
    }

    public String searchServer(String repositoryName) throws RemoteException, NotBoundException {

        servers.entrySet().forEach(entry -> {
            try {
                int port = entry.getValue();
                registry = LocateRegistry.getRegistry(port);
                PartRepository repositorioTeste = (PartRepository) registry.lookup(entry.getKey());
                if (repositoryName.equals(repositorioTeste.getNome())) {
                    repositorioCorrente = repositorioTeste;
                    System.out.println(
                            "Conectado ao " + repositorioCorrente.getNome() + " no servidor " + entry.getKey());
                }
            } catch (Exception e) {
                System.out.println("Conexão ao repositório " + repositoryName + " falhou.");
            }
        });
        return repositorioCorrente.getNome();

    }

    public void bind(String repositoryName) throws RemoteException, NotBoundException {
        try {
            String serverName = searchServer(repositoryName);
            if (serverName.isEmpty()) {
                System.out.println("Repositório não encontrado.");
                return;
            }
        } catch (Exception e) {
            System.out.println("Conexão ao repositório " + repositoryName + " falhou.");
        }
    }

    public void listp() {
        try {
            repositorioCorrente.listar();
        } catch (Exception e) {
            System.out.println("Erro ao listar as peças do repositório.");
        }
    }

    public void createp(String nome, String descricao) {
        List<Part> newSubParts = new ArrayList<Part>();
        try {
            int idCurrentPart = repositorioCorrente.createPart(nome, descricao, newSubParts);
            currentPart = repositorioCorrente.getPart(idCurrentPart);
        } catch (Exception e) {
            System.out.println("Erro ao criar uma nova peça.");
        }

    }

    public void getp(int id) {
        try {
            currentPart = repositorioCorrente.getPart(id);
            System.out.println("Peça encontrada");
        } catch (Exception e) {
            System.out.println("Erro ao buscar a peça " + id);
        }
    }

    public void showp() {
        try {
            System.out.println("Peça corrente: ");
            System.out.println("Nome" + currentPart.getNome());
            System.out.println("ID:" + currentPart.getId());
            System.out.println("Descricao: " + currentPart.getDescricao());
            System.out.println("Repositório: " + currentPart.getPartRepositoryName());
            System.out.println("É primitiva: " + currentPart.ehPrimitiva());
            System.out.println("Tamanho: " + currentPart.tamanho());
        } catch (Exception e) {
            System.out.println("Erro ao exibir os atributos da peça corrente.");
        }
    }

    public void showsubps() {
        if (subParts.isEmpty()) {
            System.out.println("Nenhuma sub-peças corrente a ser exibida.");
        } else {
            for (int i = 0; i < subParts.size(); i++) {
                subParts.get(i).entrySet().forEach(entry -> {
                    try {
                        System.out.println(entry.getKey().getNome() + " " + entry.getValue());
                    } catch (Exception e) {
                        System.out.println("Erro ao exibir as sub-peças correntes.");
                    }
                });
            }
        }

    }

    public void clearlist() {
        subParts.clear();
        System.out.println("Lista de sub-peças corrente esvaziada.");
    }

    public void addsubpart(int n) {
        try {
            if (currentPart == null) {
                System.out.println("Nenhuma peça corrente selecionada.");

            } else {
                Map<Part, Integer> newSubParts = new HashMap<Part, Integer>();
                newSubParts.put(currentPart, n);
                subParts.add(newSubParts);
                System.out.println("Adicionada à lista de peças de sub-peças corrente");
            }
        } catch (Exception e) {
            System.out.println("Erro ao inserir a sub-peças à lista.");
        }

    }

    public void addp() {
        try {
            List<Part> newSubParts = new ArrayList<Part>();
            for (int i = 0; i < subParts.size(); i++) {
                subParts.get(i).entrySet().forEach(entry -> {
                    newSubParts.add(entry.getKey());
                });
            }
            repositorioCorrente.addPart(currentPart, newSubParts);
            System.out.println("Peça adicionada ao repositório corrente");
        } catch (Exception e) {
            System.out.println("Erro ao inserir a peça corrente ao repositório.");
        }
    }

    public void help() {
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

    public void quit() {
        System.out.println("Saindo do programa...");
        System.exit(0);
    }

    public void run() throws RemoteException {
        Map<String, Integer> serverPortas = new HashMap<String, Integer>();
        serverPortas.put("serverAle", 1099);
        serverPortas.put("serverMi", 53903);
        serverPortas.put("serverSena", 53907);
        serverPortas.put("serverYumi", 53906);

        try {
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("\nDigite um comando do sistema.");
                String comando = sc.nextLine();

                if (comando.isEmpty()) {
                    System.out.println(
                            "Digite um comando do sistema. Para saber os comandos do sistema, digite \"help\"");
                    continue;
                }

                switch (comando) {
                    case "bind":
                        System.out.println("Digite o nome do repositório que deseja conectar:");
                        String repositoryName = sc.nextLine();
                        bind(repositoryName);
                        break;

                    case "listp":
                        listp();
                        break;

                    case "createp":
                        System.out.println("Digite o nome da peça que deseja criar:");
                        String nome = sc.nextLine();
                        System.out.println("Digite a descrição da peça que deseja criar:");
                        String descricao = sc.nextLine();
                        createp(nome, descricao);
                        break;

                    case "getp":
                        System.out.println("Digite o id da peça que deseja buscar:");
                        int id = sc.nextInt();
                        getp(id);
                        break;

                    case "showp":
                        showp();
                        break;

                    case "showsubps":
                        showsubps();
                        break;

                    case "clearlist":
                        clearlist();
                        break;

                    case "addsubpart":
                        System.out.println(
                                "Digite o número de unidades da peça corrente a ser inserida na lista de sub-peças:");
                        int n = sc.nextInt();
                        addsubpart(n);

                    case "addp":
                        addp();
                        break;

                    case "help":
                        help();
                        break;

                    case "quit":
                        quit();
                        break;

                    default:
                        System.out.println("Comando inválido. Digite \"help\" para obter ajuda.");

                }
            }

        } catch (Exception e) {
            System.out.println("PartClient error " + e.getMessage());
            System.exit(0);
        }

    }

    public static void main(String args[]) throws RemoteException {
        PartClient newPartClient = new PartClient();
        newPartClient.run();
    }
}