package RMI;

// Java program to implement the Search interface

import PowerPeople.flyVillain;

import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class SearchQuery extends UnicastRemoteObject implements Search {
    private String absolutePath = "/home/cianosullivan/Desktop/CIT/3rd Year/Semester 1/Java projects/DisributedSystemsProject2" +
            "/src/battleZones/battle.txt";
    private File file = new File(absolutePath);
    private flyVillain vil;

    // Default constructor to throw RemoteException
    // from its parent constructor
    public SearchQuery() throws RemoteException {
        super();
    }

    // This checks if the villain exists inside of the battle file
    private boolean villainExist(){
        boolean vilExist;

        try  {
            // Reading the object from a file
            FileInputStream file = new FileInputStream(absolutePath);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            vil = (flyVillain) in.readObject();

            // Close the unneeded streams
            in.close();
            file.close();

            // Prints if the villain exists
            System.out.println("Vilain has been deserialized and exists");
            vilExist = true;
        }

        // If there is no villain inside of the text file then one of these text files will catch it
        catch(IOException ex) {
            // Set the exist to false if the villain doesn't exist
            vilExist = false;
            System.out.println("There is no villain inside of the battle file");
        }

        catch(ClassNotFoundException ex) {
            // Set the exist to false if the villain doesn't exist
            vilExist = false;
            System.out.println("ClassNotFoundException is caught");
        }
        return vilExist;
    }

    // Implementation of the query interface
    public boolean query(String search) throws RemoteException {
        // This checks if the villain exist in the text file and if it does then it sets vil to it
        return search.equals("1") & villainExist();
    }

    public flyVillain sendVillain() throws RemoteException {
        return vil;
    }

    // This will put the array list into the battle text file
    public void sendBattle(ArrayList<Object> battleArray) throws RemoteException{
        // Serialization
        try {
            FileOutputStream file = new FileOutputStream("/home/cianosullivan/Desktop/CIT/3rd Year/Semester 1" +
                    "/Java projects/DisributedSystemsProject2/src/battleZones/battle.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);
            // Puts the array list into the file
            out.writeObject(battleArray);
            out.close();
            file.close();
            System.out.println("Battle Array has been serialized");
        }
        catch(IOException ex) {
            System.out.println("IOException is caught");
        }
        moveFile();
    }

    // Move the battle file to the battle done folder
    private void moveFile(){
        boolean checkFile = file.renameTo(new File("/home/cianosullivan/Desktop/CIT/3rd Year/Semester 1" +
                "/Java projects/DisributedSystemsProject2/src/battlesDone/battleDone.txt"));
        if(checkFile){
            System.out.println("File was moved");
        }
        else {
            System.out.println("File was not moved");
        }
    }
}

