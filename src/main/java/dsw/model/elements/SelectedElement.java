package dsw.model.elements;

import dsw.repository.composite.ClassyNode;

import java.awt.*;

public class SelectedElement extends Interclass {
    public SelectedElement(Point position, Dimension size, String name, ClassyNode parent, float stroke, Paint paint, Paint borderPaint, Paint textPaint) {
        super(position, size, name, parent, stroke, paint, borderPaint, textPaint);
    }
}
