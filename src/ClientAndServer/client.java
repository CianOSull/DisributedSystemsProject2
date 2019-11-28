package ClientAndServer;

import hero.hero;
import villain.villain;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class client {
    public static void socketTest6(){
        try {
            Socket socket = new Socket("localhost", 8001);

            // get the ouput stream from the socket
            OutputStream outputStream = socket.getOutputStream();
            // create an objecto uput stream
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            boolean checkForVillain = true;
            System.out.println("Checking for a villain");
            objectOutputStream.writeObject(checkForVillain);

            // get the input stream from the connected socket
            InputStream inputStream = socket.getInputStream();
            // create a DataInputStream so we can read data from it.
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            if((boolean) objectInputStream.readObject()){
                System.out.println("The villain Exists");

                villain vil = (villain) objectInputStream.readObject();
                System.out.println(vil.getName());

                hero her = new hero();
                ArrayList<Object> powerPeopleList = new ArrayList<>();
                powerPeopleList.add(vil);
                powerPeopleList.add(her);

                System.out.println("Sending Arraylist to the ServerSocket");
                objectOutputStream.writeObject(powerPeopleList);

            }
            else {
                System.out.println("The vilain doesn't exist");
            }

        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
