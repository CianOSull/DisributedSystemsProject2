package PowerPeople;

import java.io.Serializable;

// This is the main class for all villains
public class villain implements Serializable{
    private String name;
    private String power;

    public villain(String n, String p){
        this.name = n;
        this.power = p;

    }

    public String getName(){
        return name;
    }
}
