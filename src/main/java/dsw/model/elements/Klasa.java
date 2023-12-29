package dsw.model.elements;

import dsw.repository.composite.ClassyNode;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Klasa extends Interclass {

    private ArrayList<Atribut> atributs;
    private ArrayList<Metoda>  metode;


    public Klasa(Point position, Dimension size, String name, ClassyNode parent, float stroke, Paint paint, Paint borderPaint, Paint textPaint, ArrayList<Atribut> atributs, ArrayList<Metoda> metode) {
        super(position, size, name, parent, stroke, paint, borderPaint, textPaint);
        this.atributs = atributs;
        this.metode = metode;
    }



}
