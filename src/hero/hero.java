package hero;

import java.io.Serializable;

public class hero implements Serializable {
    private String name;

    public hero(){
        this.name = "Test Hero";
    }

    public String getName(){
        return name;
    }
}
