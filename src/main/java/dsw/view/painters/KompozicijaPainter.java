package dsw.view.painters;

import dsw.model.elements.ConnectionElement;
import dsw.model.elements.DiagramElement;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class KompozicijaPainter extends ConnectionPainter{
        public KompozicijaPainter(ConnectionElement device) {
            super(device);
            updateShape();
        }

        @Override
        public void updateShape() {
            shape=new GeneralPath();
            ((GeneralPath)shape).moveTo(((ConnectionElement)device).getPosition().x, ((ConnectionElement)device).getPosition().y);
            ((GeneralPath)shape).lineTo(((ConnectionElement)device).getSize().width, ((ConnectionElement)device).getSize().height);

//            super.updateShape(); // Let the superclass handle the basic shape setup
//
//            // Calculate positions for the composition arrow or shape
//            Point p1 = getPoint1();
//            Point p2 = getPoint2();
//
//            if (p1 != null && p2 != null) {
//                int diamondSize = 8; // Size of the diamond shape for composition
//
//                // Update the shape to include the composition diamond
//                if (shape == null) {
//                    shape = new GeneralPath(); // Initialize the shape if it's null
//                } else {
//                    ((GeneralPath) shape).reset(); // Reset the shape if it's already defined
//                }
//
//                ((GeneralPath) shape).moveTo(p2.x, p2.y);
//                ((GeneralPath) shape).lineTo(p2.x - diamondSize, p2.y - diamondSize);
//                ((GeneralPath) shape).lineTo(p2.x, p2.y - (2 * diamondSize));
//                ((GeneralPath) shape).lineTo(p2.x + diamondSize, p2.y - diamondSize);
//                ((GeneralPath) shape).closePath();
//            }
        }

    @Override
    public boolean elementAt(Point pos) {
        return false;
    }

}


