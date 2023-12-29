package dsw.model.elements;

import dsw.model.helpers.Vidljivost;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Atribut extends ClassContent{

    private String type;

    public Atribut(String name, String vidljivost, String type) {
        super(name, vidljivost);
        this.type = type;
    }


}
