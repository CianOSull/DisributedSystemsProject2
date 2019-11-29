package ClientAndServer;

import Factory.flyVillainFactory;
import PowerPeople.flyHero;
import PowerPeople.flyVillain;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class server implements Runnable {
    flyVillainFactory facotry = new flyVillainFactory();

    public void run() {
        socketTest6();
    }

    private void socketTest6(){
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8001);
            System.out.println("Socket Created");
            Socket socket = serverSocket.accept();

            // get an input stream from the socket
            InputStream inputStream = socket.getInputStream();
            // create an object input stream
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            boolean villainExist = true;

            if(((String) objectInputStream.readObject()).equals("1") & villainExist){
                OutputStream outputStream = socket.getOutputStream();
                // create an object output stream from the output stream so we can send an object through it
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(true);

                flyVillain vil = facotry.getVillain();
                objectOutputStream.writeObject(vil);

                ArrayList<Object> powerPeopleList = (ArrayList<Object>) objectInputStream.readObject();

                flyVillain vilTest = (flyVillain) powerPeopleList.get(0);
                flyHero heroTest = (flyHero) powerPeopleList.get(1);

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
