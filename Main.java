import java.rmi.RemoteException;

class Main {
    public static void main(String args[]) throws RemoteException{
        //As portas em que rodam os processos foram hardcoded.

        try{
        PartServer partServer = new PartServer("serverAle", "repoAle", 1099);
        PartServer partServer2 = new PartServer("serverMi", "repoMi", 53903);
        PartServer partServer3 = new PartServer("serverSena", "repoSena", 53907);
        PartServer partServer4 = new PartServer("serverYumi", "repoYumi", 53906);

        System.out.println("Executando o partClient: ");
        PartClient partClient = new PartClient();
        partClient.run();
        } catch(Exception e){
            System.err.println(e);
        }
    }

}