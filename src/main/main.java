package main;

import ClientAndServer.client;
import ClientAndServer.server;

public class main {
    public static void main(String[] args){
        server callServer = new server();
        client callClient = new client();

        Thread serverThread = new Thread(callServer);
        Thread clientThread = new Thread(callClient);

        serverThread.start();
        clientThread.start();
    }
}
