package ClientAndServer;

import hero.hero;
import villain.villain;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class server {
    public void socketTest6(){
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8001);
            System.out.println("Socket Created");
            Socket socket = serverSocket.accept();

            // get an input stream from the socket
            InputStream inputStream = socket.getInputStream();
            // create an object input stream
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            boolean villainExist = false;

            if((boolean) objectInputStream.readObject() == villainExist){
                OutputStream outputStream = socket.getOutputStream();
                // create an object output stream from the output stream so we can send an object through it
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(true);

                villain vil = new villain();
                objectOutputStream.writeObject(vil);

                ArrayList<Object> powerPeopleList = (ArrayList<Object>) objectInputStream.readObject();

                villain vilTest = (villain) powerPeopleList.get(0);
                hero heroTest = (hero) powerPeopleList.get(1);

                System.out.println("ArrayList 1 >>>: " + vilTest.getName());
                System.out.println("ArrayList 2 >>>: " + heroTest.getName());
            }
            else {
                OutputStream outputStream = socket.getOutputStream();
                // create an object output stream from the output stream so we can send an object through it
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(false);
            }
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
