package RMI;

//program for client application

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;

public class ClientRequest implements Runnable {
    private String rmiCon = "rmi://localhost:1901";
    private String name = "/rmiclient";

    public void run(){
        try {
            // The thread wil sleep for a few seconds to let the server get ready
            Thread.sleep(3000);
            System.out.println("Client is starting");

            // This creates the socket needed to call
            Search access = (Search) Naming.lookup("rmi://localhost:1900" +  "/rmiserver");
            clientServer(access);
            access.addClient(rmiCon, name);
            access.query(1);
        }
        catch (InterruptedException | IOException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    private void clientServer(Search access) {
        try {
            // Create an object of the interface
            // implementation class
            Client clientAct = new ClientAct(access);

            // rmiregistry within the server JVM with
            // port number 1900
            LocateRegistry.createRegistry(1901);

            // Binds the remote object by the name
            // geeksforgeeks
            Naming.rebind(rmiCon + name, clientAct);
        }
        catch(Exception ae) {
            System.out.println(ae);
        }
    }
}
