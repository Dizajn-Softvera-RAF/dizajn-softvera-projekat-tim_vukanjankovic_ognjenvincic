package dsw.view.painters;

import dsw.model.elements.ConnectionElement;
import dsw.model.elements.DiagramElement;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class GeneralizacijaPainter extends ConnectionPainter {

        public GeneralizacijaPainter(ConnectionElement device) {
            super(device);
            updateShape();
        }

        @Override
        public void updateShape() {
            shape=new GeneralPath();
            ((GeneralPath)shape).moveTo(((ConnectionElement)device).getPosition().x, ((ConnectionElement)device).getPosition().y);
            ((GeneralPath)shape).lineTo(((ConnectionElement)device).getSize().width, ((ConnectionElement)device).getSize().height);
            // Calculate positions for the arrowhead
//            Point p1 = getPoint1();
//            Point p2 = getPoint2();
//
//            if (p1 != null && p2 != null) {
//                int arrowLength = 10; // Length of the arrowhead
//                double angle = Math.atan2(p2.y - p1.y, p2.x - p1.x);
//                int x3 = p2.x - (int) (arrowLength * Math.cos(angle - Math.PI / 6));
//                int y3 = p2.y - (int) (arrowLength * Math.sin(angle - Math.PI / 6));
//                int x4 = p2.x - (int) (arrowLength * Math.cos(angle + Math.PI / 6));
//                int y4 = p2.y - (int) (arrowLength * Math.sin(angle + Math.PI / 6));
//
//                // Update the shape to include the arrowhead for generalization
//                if (shape == null) {
//                    shape = new GeneralPath(); // Initialize the shape if it's null
//                } else {
//                    ((GeneralPath) shape).reset(); // Reset the shape if it's already defined
//                }
//
//                ((GeneralPath) shape).moveTo(p2.x, p2.y);
//                ((GeneralPath) shape).lineTo(x3, y3);
//                ((GeneralPath) shape).lineTo(x4, y4);
////                ((GeneralPath) shape).closePath();
//            }
        }

    @Override
    public boolean elementAt(Point pos) {
        return false;
    }
}

