package dsw.model.elements;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public class RectangleElement extends DiagramDevice{

    public RectangleElement(Point position, Dimension size, float stroke, Paint paint, Paint borderPaint, Paint textPaint) {
        super(position, size, stroke, paint, borderPaint, textPaint);
    }

}
