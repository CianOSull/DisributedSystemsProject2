package Factory;

import PowerPeople.flyVillain;

import java.io.Serializable;

// Check what each class does because for some reason this needs seralization when running
public class flyVillainFactory implements villainFactory, Serializable {

    private int counter = 0;
    private String power = "fly";

    // This factory creates a new fly villain
    public flyVillain getVillain(){ counter++; return new flyVillain(Integer.toString(counter), power);  }

}
