# Java Remote Method Invocation (RMI)

Implementação de um sistema de informações distribuído sobre peças ou componentes (parts) usando Remote Method Invocation (RMI) de Java.

Estudo-Programa desenvolvido para a disciplina **ACH2147 - Desenvolvimento de Sistemas de Informação Distribuídos**.

### java version:
    openjdk 14 2020-03-17

### **Instruções de compilação/execução**
No repositório do EP:  
Para compilar os arquivos *.java:
```
make default
```
Para criar novos servidores de forma simplificada, instancie novos objetos do tipo PartServer no Main.java. O mesmo vale para os clientes.
Para criar novos servidores além do especificados pelo Main.java, deve-se adicionar o nome do server e a porta no qual está sendo executado o server no método createServerList() do PartClient.java

Para executar o EP (criando os servidores e objetos do tipo PartClient):
```
make start-server
```
Para criar um servidor (se não especificar o hostname na instância, será utilizado o 'localhost' como default):
```
make server
```
Para instanciar um objeto do tipo PartClient:
```
make client
```

**Integrantes**  
Alexandre Kenji Okamoto - 11208371  
Guilherme Mutschele Sena - 11208304  
Lia Yumi Morimoto - 9364802  
Michelly Rodrigues da Silva - 11270607
