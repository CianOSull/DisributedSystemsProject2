package Factory;

import PowerPeople.flyHero;

import java.io.Serializable;

// Check what each class does because for some reason this needs seralization when running
public class flyHeroFactory implements heroFactory, Serializable {

    private int counter = 0;
    private String power = "fly";

    // This factory creates a new fly hero
    public flyHero getHero(){ counter++; return new flyHero(Integer.toString(counter), power);  }

}
