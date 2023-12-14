package dsw.view.painters;

import dsw.model.elements.ConnectionElement;
import dsw.model.elements.DiagramElement;
import dsw.model.elements.Interclass;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;

public class GeneralizacijaPainter extends ConnectionPainter {

    public GeneralizacijaPainter(ConnectionElement device) {
        super(device);
        updateShape();
    }

        @Override
        public void updateShape() {
            Point p1 = getCenter(getPoint1()); // Centar početnog elementa
            Point p2 = getCenter(getPoint2()); // Centar krajnjeg elementa

            if (p1 != null && p2 != null) {
                shape = new GeneralPath();

                double angle = Math.atan2(p2.y - p1.y, p2.x - p1.x); // Ugao linije

                // Izračunavanje tačaka za strelicu
                int arrowLength = 15;
                int arrowWidth = 10;
                int x3 = p2.x - (int) (arrowLength * Math.cos(angle));
                int y3 = p2.y - (int) (arrowLength * Math.sin(angle));
                int x4 = x3 - (int) (arrowWidth * Math.cos(angle - Math.PI / 2));
                int y4 = y3 - (int) (arrowWidth * Math.sin(angle - Math.PI / 2));
                int x5 = x3 - (int) (arrowWidth * Math.cos(angle + Math.PI / 2));
                int y5 = y3 - (int) (arrowWidth * Math.sin(angle + Math.PI / 2));

                // Crtanje linije
                ((GeneralPath) shape).moveTo(p1.x, p1.y);
                ((GeneralPath) shape).lineTo(x3, y3);

                // Crtanje strelice kao poligona
                GeneralPath arrowHead = new GeneralPath();
                arrowHead.moveTo(p2.x, p2.y);
                arrowHead.lineTo(x4, y4);
                arrowHead.lineTo(x5, y5);
                arrowHead.closePath();

                // Dodavanje strelice u ukupni oblik
                ((GeneralPath) shape).append(arrowHead, false);
            }
        }

    private Point getCenter(Point p) {
    // Implementacija za dobijanje centra elementa
    return p; // Ovo je primer, prilagodite prema vašoj implementaciji
    }

    @Override
    public boolean elementAt(Point pos) {
        // Implementacija ako želite proveru da li se kliknulo na element
        return false;
    }
}



