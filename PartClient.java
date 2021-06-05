import java.net.*;
import java.rmi.*;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

public class PartClient{
    public static void main(String args[]){
        HostnameVerifier hv = new HostnameVerifier(){
            public boolean verify(String hostname, SSLSession session){
                return true;
            }
        };

        try {
            Part obj = (Part)Naming.lookup("localhost"); //nomedamaquina que fica no etc/hosts (ubuntu)
            System.out.println(obj.sayHello());
        } catch(Exception e) {
            System.out.println("PartClient error"+ e.getMessage());
        }
        System.exit(0);
    }
}