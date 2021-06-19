class Main {
    public static void main(String args[]){
        PartServer partServer = new PartServer("serverAle", "repoAle", 1099);
        PartServer partServer2 = new PartServer("serverMi", "repoMi", 53903);
        PartServer partServer3 = new PartServer("serverSena", "repoSena", 53907);
        PartServer partServer4 = new PartServer("serverYumi", "repoYumi", 53906);

        System.out.println("Executando o partClient: ");
        PartClient partClient = new PartClient();
        partClient.run();
    }

}