package RMI;

//program for server application


import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class SearchServer implements Runnable {
    public void run() {
        System.out.println("Server has started");

        // Geeks for geeks tutorial on rmi, clients connects to server and calls its method
        searchServer();
    }

    // This c
    private void searchServer() {
        try {
            // Create an object of the interface
            // implementation class
            Search search = new SearchQuery();

            // rmiregistry within the server JVM with
            // port number 1900
            LocateRegistry.createRegistry(1900);

            // Binds the remote object by the name
            // geeksforgeeks
            Naming.rebind("rmi://localhost:1900"+ "/rmiserver", search);
        }
        catch(Exception ae) {
            System.out.println(ae);
        }
    }
}
