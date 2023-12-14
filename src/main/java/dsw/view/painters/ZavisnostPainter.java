package dsw.view.painters;

import dsw.model.elements.ConnectionElement;
import dsw.model.elements.DiagramElement;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class ZavisnostPainter extends ConnectionPainter {

    public ZavisnostPainter(ConnectionElement device) {
        super(device);
        updateShape();
    }

    @Override
    public void updateShape() {
        shape=new GeneralPath();
        ((GeneralPath)shape).moveTo(((ConnectionElement)device).getPosition().x, ((ConnectionElement)device).getPosition().y);
        ((GeneralPath)shape).lineTo(((ConnectionElement)device).getSize().width, ((ConnectionElement)device).getSize().height);

//        super.updateShape(); // Let the superclass handle the basic shape setup
//
//        // Calculate positions for the dependency arrow or shape
//        Point p1 = getPoint1();
//        Point p2 = getPoint2();
//
//        if (p1 != null && p2 != null) {
//            // Update the shape to include the dependency dashed line
//            if (shape == null) {
//                shape = new GeneralPath(); // Initialize the shape if it's null
//            } else {
//                ((GeneralPath) shape).reset(); // Reset the shape if it's already defined
//            }
//
//            // Create the dashed line manually using individual segments
//            float[] dashPattern = {4}; // Adjust this array to change the dash pattern
//            boolean isOn = true;
//            float[] segment = new float[2];
//            segment[0] = p1.x;
//            segment[1] = p1.y;
//
//            while (segment[0] <= p2.x && segment[1] <= p2.y) {
//                if (isOn) {
//                    ((GeneralPath) shape).moveTo(segment[0], segment[1]);
//                    isOn = false;
//                } else {
//                    ((GeneralPath) shape).lineTo(segment[0], segment[1]);
//                    isOn = true;
//                }
//                segment[0] += dashPattern[0];
//                segment[1] += dashPattern[0];
//            }
//        }
    }

    @Override
    public boolean elementAt(Point pos) {
        return false;
    }

}

