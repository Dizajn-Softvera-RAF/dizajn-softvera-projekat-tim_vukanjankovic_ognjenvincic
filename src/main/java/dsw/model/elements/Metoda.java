package dsw.model.elements;

import dsw.model.helpers.Vidljivost;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Metoda extends ClassContent{

    private String returnType;


    public Metoda(String name, String vidljivost, String returnType) {
        super(name, vidljivost);
        this.returnType = returnType;
    }


}
