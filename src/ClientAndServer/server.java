package ClientAndServer;
import Factory.flyVillainFactory;
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

    public void run() {
        //ServerSocket serverSocket = null;
        try {
            // This creates the socket the server will be placed on
            ServerSocket serverSocket = new ServerSocket(8001);
            System.out.println("Socket Created");
            Socket socket = serverSocket.accept();

            // This while loop will keep the server going until it has moved the battle file
            while(check) {
                runServerSocket(socket);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    // This checks if the villain exists inside of the battle file
    private boolean villainExist(){
        boolean vilExist;

        try  {
            // Reading the object from a file
            FileInputStream file = new FileInputStream(absolutePath);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            flyVillain vil = (flyVillain) in.readObject();

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

    // This will put the array list into the battle text file
    private void sendBattle(ArrayList<Object> battleArray){
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

        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }
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

    // This is the funcitonality of the server and handles any inputs or outputs of the server
    private void runServerSocket(Socket socket){
        try {
            // Get an input stream from the socket
            InputStream inputStream = socket.getInputStream();
            // Create an object input stream
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            // This is a poll check, when it recieves a one it will check if the villain exists
            if(objectInputStream.readObject().equals("1") & villainExist()){
                // Make an output stream
                OutputStream outputStream = socket.getOutputStream();
                // Create an object output stream from the output stream so we can send an object through it
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                // This lets the client know the villain does exist and to ready itself to recieve it
                objectOutputStream.writeObject(true);

                // Create the villain
                flyVillain vil = facotry.getVillain();
                // Send the villain to the client
                objectOutputStream.writeObject(vil);

                // Create an arraylist to recieve the arraylist client will send
                ArrayList<Object> powerPeopleList = (ArrayList<Object>) objectInputStream.readObject();

                // Put the array list into the battle file
                sendBattle(powerPeopleList);
                // Move the file to the battles done folder
                moveFile();

                // End the while loop as the server is done
                check = false;
            }
            else {
                OutputStream outputStream = socket.getOutputStream();
                // Create an object output stream from the output stream so we can send an object through it
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

                // This will let the object know that villain doesn't exist
                objectOutputStream.writeObject(false);
            }
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
