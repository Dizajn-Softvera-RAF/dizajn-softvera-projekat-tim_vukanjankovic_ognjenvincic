package dsw.model.elements;

import dsw.model.helpers.Vidljivost;

public abstract class ClassContent {

    private String name;
    private String vidljivost;

    public ClassContent(String name, String vidljivost) {
        this.name = name;
        this.vidljivost = vidljivost;
    }
}
