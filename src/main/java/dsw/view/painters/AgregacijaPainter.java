package dsw.view.painters;

import dsw.model.elements.ConnectionElement;
import dsw.model.elements.DiagramElement;
import dsw.model.elements.Interclass;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class AgregacijaPainter extends ConnectionPainter {

    public AgregacijaPainter(ConnectionElement device) {
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
    @Override
    public void paint(Graphics2D g, DiagramElement element) {

    }

    @Override
    public boolean elementAt(Point pos) {
        return false;
    }
}
