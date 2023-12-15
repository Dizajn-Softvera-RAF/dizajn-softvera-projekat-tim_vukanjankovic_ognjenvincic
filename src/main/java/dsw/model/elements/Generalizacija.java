package dsw.model.elements;

import dsw.model.helpers.ConnectionLine;
import dsw.repository.composite.ClassyNode;

import java.awt.*;

public class Generalizacija extends ConnectionElement{

    public Generalizacija(Point position, Dimension size, String name, ClassyNode parent, float stroke, Paint paint, Paint borderPaint, Paint TextPaint, ConnectionLine connectionLine) {
        super(position, size, name, parent, stroke, paint, borderPaint, TextPaint, connectionLine);
    }
}
