package dsw.view.painters;

import dsw.core.Config;
import dsw.model.elements.DiagramDevice;
import dsw.model.elements.RectangleElement;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import static sun.audio.AudioDevice.device;

public class RectanglePainter extends DevicePainter{

    public RectanglePainter(DiagramDevice device) {

        super(device);

        if (device.getPojamShape() == null) {
            device.setPojamShape(Config.SHAPE);
        }

        updateShape();
    }

    private void updateShape(){
        RectangleElement rectangle = (RectangleElement) device;
        if (rectangle.getPojamShape() == Shapes.MAIN) {
            shape = new Ellipse2D.Float(rectangle.getPosition().x-rectangle.getSize().width/2, rectangle.getPosition().y-rectangle.getSize().height/2, rectangle.getSize().width, rectangle.getSize().height);
        }
        if (rectangle.getPojamShape() == Shapes.ELLIPSE) {
            shape = new Ellipse2D.Float(rectangle.getPosition().x-rectangle.getSize().width/2, rectangle.getPosition().y-rectangle.getSize().height/2, rectangle.getSize().width, rectangle.getSize().height);
        } else if (rectangle.getPojamShape() == Shapes.RECTANGLE) {
            shape = new Rectangle2D.Float(rectangle.getPosition().x-rectangle.getSize().width/2, rectangle.getPosition().y-rectangle.getSize().height/2, rectangle.getSize().width, rectangle.getSize().height);
        } else if (rectangle.getPojamShape() == Shapes.ROUNDED_RECTANGLE) {
            shape = new RoundRectangle2D.Float(rectangle.getPosition().x-rectangle.getSize().width/2, rectangle.getPosition().y-rectangle.getSize().height/2, rectangle.getSize().width, rectangle.getSize().height,20,20);
        }
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
