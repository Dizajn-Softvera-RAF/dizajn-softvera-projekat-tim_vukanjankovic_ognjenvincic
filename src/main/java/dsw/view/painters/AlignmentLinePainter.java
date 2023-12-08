package dsw.view.painters;

import dsw.model.elements.Interclass;

import java.awt.*;
import java.awt.geom.Line2D;

public class AlignmentLinePainter extends InterclassPainter {

    public AlignmentLinePainter(Interclass device) {
        super(device);
        updateShape();
    }

    private void updateShape() {
        if (device.getPosition().getX() > -1) {
            shape = new Line2D.Float(device.getPosition().x, 0, device.getPosition().x, 2000);
        } else if (device.getPosition().getY() > -1) {
            shape = new Line2D.Float(0, device.getPosition().y, 2000, device.getPosition().y);
        } else {
            shape = new Line2D.Float(0, -1, 1000, -1);
        }
    }

    public void setPosition(Point p) {
        device.setPosition(p);
        updateShape();
    }

}
