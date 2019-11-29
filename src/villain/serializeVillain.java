package villain;

import Factory.flyVillainFactory;
import PowerPeople.flyVillain;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class serializeVillain implements Runnable{
    private flyVillainFactory factory = new flyVillainFactory();

    public void run(){
        try {
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        makeVillain();
    }

    private void makeVillain(){
        flyVillain fly = factory.getVillain();

        // Serialization
        try
        {
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream("/home/cianosullivan/Desktop/CIT/3rd Year/Semester 1" +
                    "/Java projects/DisributedSystemsProject2/src/battleZones/battle.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(fly);

            out.close();
            file.close();

            System.out.println("\nVillain has been serialized\n");

        }

        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }
    }
}
