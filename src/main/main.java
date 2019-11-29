package main;

import ClientAndServer.client;
import ClientAndServer.server;
import villain.serializeVillain;

public class main {
    public static void main(String[] args){
        // This is the server thread
        server callServer = new server();

        // This is the client thread
        client callClient = new client();

        // This thread creates the villain after a certain amount of time
        serializeVillain ser = new serializeVillain();

        // Create all the threads to be used
        Thread serverThread = new Thread(callServer);
        Thread clientThread = new Thread(callClient);
        Thread villainThread = new Thread(ser);

        // Start them all
        serverThread.start();
        clientThread.start();
        villainThread.start();
    }
}
