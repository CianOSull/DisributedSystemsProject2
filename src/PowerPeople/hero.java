package PowerPeople;

import java.io.Serializable;

// This is the main class for all heros
public class hero implements Serializable {
    private String name;
    private String power;

    public hero(String n, String p){
        this.name = n;
        this.power = p;
    }

    public String getName(){
        return name;
    }

}
