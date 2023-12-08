package dsw.model.elements;

import dsw.model.helpers.ConnectionLine;

import java.awt.*;

public class Generalizacija extends ConnectionElement{

    public Generalizacija(Point position, Dimension size, float stroke, Paint paint, Paint borderPaint, Paint TextPaint, ConnectionLine connectionLine) {
        super(position, size, stroke, paint, borderPaint, TextPaint, connectionLine);
    }
}
