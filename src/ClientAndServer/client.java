package ClientAndServer;

import Factory.flyHeroFactory;
import PowerPeople.flyHero;
import PowerPeople.flyVillain;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class client implements Runnable {
    private flyHeroFactory factory = new flyHeroFactory();
    private boolean check = true;

    public void run() {
        try {
            // The thread wil sleep for a few seconds to let the server get ready
            Thread.sleep(3000);

            // This creates the socket needed to call
            Socket socket = new Socket("localhost", 8001);

            // This will keep the client polling until a villain is found
            while(check) {
                runClient(socket);
                // If the villain doesn't exist yet then it will sleep and try again later
                Thread.sleep(3000);
            }
        }
        catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    private void runClient(Socket socket){
        try {
            // get the ouput stream from the socket
            OutputStream outputStream = socket.getOutputStream();
            // create an objecto uput stream
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            System.out.println("Checking for a villain");

            // This is the poll feature, it sends the server a 1 to let the server know to check for a villain
            objectOutputStream.writeObject("1");

            // This part here is for recieving the reply from the server
            InputStream inputStream = socket.getInputStream();
            // create a DataInputStream so we can read data from it.
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            // If the server replies wth a true then the villain exists
            if((boolean) objectInputStream.readObject()){
                System.out.println("The villain Exists, preparing to recieve it");

                // It receives the villain from the server
                flyVillain vil = (flyVillain) objectInputStream.readObject();
                System.out.println("The villain was recieved");

                // Create a hero to deal with the villain
                flyHero flyHer = factory.getHero();

                // Create an arraylist to store the hero and villain and add them to it
                ArrayList<Object> powerPeopleList = new ArrayList<>();
                powerPeopleList.add(vil);
                powerPeopleList.add(flyHer);

                // Send the arraylist ot hte server
                System.out.println("Sending Arraylist to the ServerSocket");
                objectOutputStream.writeObject(powerPeopleList);

                // The clients work is done
                check = false;
            }

            // If villain doesn't exist the tell the console
            else {
                System.out.println("The vilain doesn't exist");
            }

        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
