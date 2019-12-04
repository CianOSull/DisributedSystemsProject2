package main;

import RMI.ClientRequest;
import RMI.SearchServer;
import villain.serializeVillain;

public class rmiMain {
    public static void main(String[] args) {
        ClientRequest client = new ClientRequest();
        SearchServer server = new SearchServer();

        // This thread creates the villain after a certain amount of time
        serializeVillain ser = new serializeVillain();

        // Create all the threads to be used
        Thread serverThread = new Thread(server);
        Thread clientThread = new Thread(client);
        Thread villainThread = new Thread(ser);

        serverThread.start();
        clientThread.start();
        villainThread.start();
    }
}
