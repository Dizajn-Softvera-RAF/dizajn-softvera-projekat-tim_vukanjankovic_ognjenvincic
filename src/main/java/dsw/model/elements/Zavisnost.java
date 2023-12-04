package dsw.model.elements;

import dsw.model.helpers.ConnectionLine;

import java.awt.*;

public class Zavisnost extends ConnectionElement{
    public Zavisnost(Point position, Dimension size, float stroke, Paint paint, Paint borderPaint, Paint textPaint, ConnectionLine connectionLine) {
        super(position, size, stroke, paint, borderPaint, textPaint, connectionLine);
    }
}
