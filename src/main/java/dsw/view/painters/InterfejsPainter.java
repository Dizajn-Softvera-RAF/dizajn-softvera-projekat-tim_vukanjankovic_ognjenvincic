package dsw.view.painters;

import dsw.core.Config;
import dsw.model.elements.DiagramDevice;
import dsw.model.elements.RectangleElement;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class InterfejsPainter extends DevicePainter{
    public InterfejsPainter(DiagramDevice device) {

        super(device);

        if (device.getPojamShape() == null) {
            device.setPojamShape(Config.SHAPE);
        }

        updateShape();
    }

    private void updateShape(){
        RectangleElement rectangle = (RectangleElement) device;
        shape = new Rectangle2D.Float(rectangle.getPosition().x-rectangle.getSize().width/2, rectangle.getPosition().y-rectangle.getSize().height/2, rectangle.getSize().width, rectangle.getSize().height);
    }

    public void resize(Dimension size){
        device.setSize(size);
        updateShape();
    }

    public void reposition(Point position){
        ((RectangleElement) device).setPosition(position);
        updateShape();
    }
}
