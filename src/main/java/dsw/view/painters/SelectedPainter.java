package dsw.view.painters;

import dsw.model.elements.Interclass;
import dsw.model.elements.SelectedElement;

import java.awt.*;
import java.awt.geom.Rectangle2D;
public class SelectedPainter extends InterclassPainter {

    private SelectedElement rectangle;

    public SelectedPainter(Interclass d1) {
        super(d1);
        rectangle = (SelectedElement) d1;
        updateShape();
    }

    public void setPosition(Point p1, Dimension d1){
        ((SelectedElement)device).setPosition(p1);
        ((SelectedElement)device).setDimension(d1);
        updateShape();
    }

    public void updateShape(){

        float height = rectangle.getSize().height + device.getStrokeWidth();
        float width = rectangle.getSize().width + device.getStrokeWidth();
        shape = new Rectangle2D.Float(rectangle.getPosition().x, rectangle.getPosition().y, width, height);

    }

}
