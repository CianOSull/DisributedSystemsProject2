package ClientAndServer;

import Factory.flyVillainFactory;
import PowerPeople.flyHero;
import PowerPeople.flyVillain;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class server implements Runnable {
    private flyVillainFactory facotry = new flyVillainFactory();
    private boolean check = true;
    private String absolutePath = "/home/cianosullivan/Desktop/CIT/3rd Year/Semester 1/Java projects/DisributedSystemsProject2" +
            "/src/battleZones/battle.txt";
    private File file = new File(absolutePath);
    private boolean checkFile;

    public void run() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8001);
            System.out.println("Socket Created");
            Socket socket = serverSocket.accept();

            while(check) {
                socketTest6(socket);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean villainExist(){
        boolean vilExist;

        // Deserialization
        try
        {
            // Reading the object from a file
            FileInputStream file = new FileInputStream(absolutePath);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            flyVillain vil = (flyVillain) in.readObject();

            in.close();
            file.close();

            System.out.println("Object has been deserialized ");
            vilExist = true;
            check = false;
            //System.out.println(vil.getName());
        }
        catch(IOException ex) {
            vilExist = false;
            System.out.println("IThere is no villain inside of the file");
        }

        catch(ClassNotFoundException ex) {
            vilExist = false;
            System.out.println("ClassNotFoundException is caught");
        }
        return vilExist;
    }

    private void moveFile(){
        checkFile = file.renameTo(new File("/home/cianosullivan/Desktop/CIT/3rd Year/Semester 1" +
                "/Java projects/DisributedSystemsProject2/src/battlesDone/battleDone.txt"));
        if(checkFile){
            System.out.println("File was moved");
        }
        else {
            System.out.println("Failed");
        }
    }

    private void socketTest6(Socket socket){
        try {
            // get an input stream from the socket
            InputStream inputStream = socket.getInputStream();
            // create an object input stream
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            // This is a poll check, when it recieves a one it will check if the villain exists
            if(((String) objectInputStream.readObject()).equals("1") & villainExist()){
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
                moveFile();
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
