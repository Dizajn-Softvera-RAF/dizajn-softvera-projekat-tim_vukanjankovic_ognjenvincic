package dsw.view.painters;

import dsw.model.elements.ConnectionElement;
import dsw.model.elements.DiagramElement;
import dsw.model.elements.Interclass;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class AgregacijaPainter extends ConnectionPainter {

    public AgregacijaPainter(DiagramElement device) {
        super(device);
        updateShape();
    }

    public void updateShape() {
        shape=new GeneralPath();
        ((GeneralPath)shape).moveTo(((ConnectionElement)device).getPosition().x, ((ConnectionElement)device).getPosition().y);
        ((GeneralPath)shape).lineTo(((ConnectionElement)device).getSize().width, ((ConnectionElement)device).getSize().height);
    }

    public void setPosition(Point p1, Point p2) {
        ((ConnectionElement)device).setPosition(p1);
        ((ConnectionElement)device).setSize(new Dimension(p2.x, p2.y));
        updateShape();
    }

    @Override
    public boolean elementAt(Point pos) {
        return false;
    }
}
