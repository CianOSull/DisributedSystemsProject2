package main;

import ClientAndServer.client;
import ClientAndServer.server;
import test.serializeVillain;

public class main {
    public static void main(String[] args){
        server callServer = new server();
        client callClient = new client();

        // This is for testing purposes. It makes a villain in the text file to be read
        serializeVillain ser = new serializeVillain();
        //ser.makeVillain();

        Thread serverThread = new Thread(callServer);
        Thread clientThread = new Thread(callClient);
        Thread villainThread = new Thread(ser);

        serverThread.start();
        clientThread.start();
        villainThread.start();
    }
}
