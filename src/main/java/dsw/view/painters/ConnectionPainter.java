package dsw.view.painters;

import dsw.model.elements.DiagramDevice;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class ConnectionPainter extends DevicePainter{
    public ConnectionPainter(DiagramDevice device) {
        super(device);
        updateShape();
    }

    public void updateShape() {
        shape=new GeneralPath();
        ((GeneralPath)shape).moveTo(device.getPosition().x,device.getPosition().y);
        ((GeneralPath)shape).lineTo(device.getSize().width, device.getSize().height);
    }

    public void setPosition(Point p1, Point p2) {
        device.setPosition(p1);
        device.setSize(new Dimension(p2.x, p2.y));
        updateShape();
    }

    public Point getPoint1() {
        return device.getPosition();
    }
    public Point getPoint2() {
        return new Point(device.getSize().width, device.getSize().height);
    }
}

