package dsw.model.elements;

import dsw.model.helpers.ConnectionLine;

import java.awt.*;

public class Agregacija extends ConnectionElement{

    public Agregacija(Point position, Dimension size, float stroke, Paint paint, Paint borderPaint, Paint textPaint, ConnectionLine connectionLine) {
        super(position, size, stroke, paint, borderPaint, textPaint, connectionLine);
    }
}
