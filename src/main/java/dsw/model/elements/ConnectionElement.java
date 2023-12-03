package dsw.model.elements;

import dsw.model.helpers.ConnectionLine;
import dsw.view.painters.DevicePainter;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public class ConnectionElement extends DiagramDevice{

    @Getter
    @Setter
    private ConnectionLine connectionLine;
    @Getter
    @Setter
    private boolean selected;
    @Getter
    @Setter
    DevicePainter device1;
    @Getter
    @Setter
    DevicePainter device2;

    public ConnectionElement(Point position, Dimension size, float stroke, Paint paint, Paint borderPaint, Paint textPaint, ConnectionLine connectionLine) {
        super(position, size, stroke, paint, borderPaint, textPaint);
        this.connectionLine = connectionLine;
        selected = false;
    }
}
