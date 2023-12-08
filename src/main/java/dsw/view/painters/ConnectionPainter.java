package dsw.view.painters;

import dsw.model.elements.ConnectionElement;
import dsw.model.elements.DiagramElement;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.GeneralPath;

public abstract class ConnectionPainter extends ElementPainter{

    protected Shape shape;
    @Getter
    @Setter
    protected ConnectionElement device;
    public ConnectionPainter(DiagramElement device) {
        super(device);
        updateShape();
    }

    public void updateShape() {
    }

    public void setPosition(Point p1, Point p2) {
    }

    public Point getPoint1() {
        return device.getPosition();
    }
    public Point getPoint2() {
        return new Point(device.getSize().width, device.getSize().height);
    }
}

