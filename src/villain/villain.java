package villain;

import java.io.Serializable;

public class villain implements Serializable {
    private String name;

    public villain(){
        this.name = "test villain";
    }
    public String getName(){
        return this.name;
    }
}
