package RMI;

//program for client application

import Factory.flyHeroFactory;
import PowerPeople.flyHero;
import PowerPeople.flyVillain;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.util.ArrayList;

public class ClientRequest implements Runnable {
    private flyHeroFactory factory = new flyHeroFactory();
    private boolean check = true;

    public void run(){
        try {
            // The thread wil sleep for a few seconds to let the server get ready
            Thread.sleep(3000);
            System.out.println("Client is starting");

            // This creates the socket needed to call
            Search access = (Search) Naming.lookup("rmi://localhost:1900" +  "/rmiserver");

            // This will keep the client polling until a villain is found
            while(check) {
                // Gets the villain from server, creates hero and puts into arraylist and then sends it
                serverPoll(access);
                // If the villain doesn't exist yet then it will sleep and try again later
                Thread.sleep(3000);
            }
        }
        catch (InterruptedException | IOException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    // This tests recieving villains
    private void serverPoll( Search access) {
        String poll = "1";
        boolean answer;
        flyVillain result;

        try {
            // lookup method to find reference of remote object
            answer = access.query(poll);

            if(answer){
                result = access.sendVillain();
                System.out.println("Status of the villain: " + result.getName());

                ArrayList<Object> peopleList = new ArrayList<Object>();
                flyHero her = factory.getHero();
                peopleList.add(result);
                peopleList.add(her);

                access.sendBattle(peopleList);

                check = false;
            }
            else{
                System.out.println("Status of the villain: Doesn't exist");
            }
        }
        catch(Exception ae) {
            System.out.println(ae);
        }
    }
}
