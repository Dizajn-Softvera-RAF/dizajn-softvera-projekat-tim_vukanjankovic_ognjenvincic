package dsw.view.painters;

import dsw.model.elements.ConnectionElement;
import dsw.model.elements.DiagramElement;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.GeneralPath;

public abstract class ConnectionPainter extends ElementPainter {

    @Getter
    @Setter
    protected Shape shape;

    //    protected ConnectionElement device;
    public ConnectionPainter(DiagramElement device) {
        super(device);
        updateShape();
    }

    public ConnectionElement getDevice() {
        return (ConnectionElement) device;
    }

    public void setDevice(ConnectionElement device) {
        this.device = device;
    }

    public void updateShape() {
    }

    public void setPosition(Point p1, Point p2) {
    }

    public void paint(Graphics2D g, DiagramElement element, boolean shouldFill) {
        device = element;

        g.setPaint(element.getBorderPaint());
        g.setStroke(element.getStroke());

        g.draw(getShape());
        g.setPaint(element.getPaint());

        if (shouldFill) {
            g.fill(getShape());
        } else {
            g.draw(getShape());
        }
    }

    public Point getPoint1() {
        return ((ConnectionElement) device).getPosition();
    }

    public Point getPoint2() {
        return new Point(((ConnectionElement) device).getSize().width, ((ConnectionElement) device).getSize().height);
    }
}

