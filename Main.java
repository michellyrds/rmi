class Main {
    public static void main(String args[]){
        PartServer partServer = new PartServer("server do ale", "repositorio do ale", 1099);
        PartServer partServer2 = new PartServer("server da mi", "repositorio da michelly", 53903);
        PartServer partServer3 = new PartServer("server do gui", "repositorio do gui", 53907);
        PartServer partServer4 = new PartServer("server da yumi", "repositorio da yumi", 53906);

        System.out.println("Executando o partClient: ");
        PartClient partClient = new PartClient();
        partClient.run();
    }

}