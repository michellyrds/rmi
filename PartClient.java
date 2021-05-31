import java.net.*;
import java.rmi.*;

public class PartClient{
    public static void main(String args[]){
        try {
            Part obj = (Part)Naming.lookup("rmi://michelly/rmi/PartServer");
            System.out.println(obj.sayHello());
        } catch(Exception e) {
            System.out.println("HelloClient erro"+ e.getMessage());
        }
        System.exit(0);
    }
}