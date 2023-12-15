package dsw.model.elements;

import dsw.repository.composite.ClassyNode;

import java.awt.*;

public class RectangleElement extends Interclass {

    public RectangleElement(Point position, Dimension size, String name, ClassyNode parent, float stroke, Paint paint, Paint borderPaint, Paint textPaint) {
        super(position, size, name, parent, stroke, paint, borderPaint, textPaint);
    }

}
