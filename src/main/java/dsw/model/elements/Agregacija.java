package dsw.model.elements;

import dsw.model.helpers.ConnectionLine;
import dsw.repository.composite.ClassyNode;

import java.awt.*;

public class Agregacija extends ConnectionElement{

    public Agregacija(Point position, Dimension size, String name, ClassyNode parent, float stroke, Paint paint, Paint borderPaint, Paint textPaint, ConnectionLine connectionLine) {
        super(position, size, name, parent, stroke, paint, borderPaint, textPaint, connectionLine);
    }
}
