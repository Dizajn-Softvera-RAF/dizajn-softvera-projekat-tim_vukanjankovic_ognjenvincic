package dsw.view.painters;

import dsw.model.elements.ConnectionElement;
import dsw.model.elements.DiagramElement;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class ZavisnostPainter extends ConnectionPainter {

    private float[] dashPattern = {5.0f, 5.0f}; // Pattern za isprekidanu liniju
    private ConnectionElement device;

    public ZavisnostPainter(ConnectionElement device) {
        super(device);
        this.device = device;
        updateShape();
    }

    @Override
    public void updateShape() {
        Point p1 = getCenter(getPoint1()); // Centar početnog elementa
        Point p2 = getCenter(getPoint2()); // Centar krajnjeg elementa

        if (p1 != null && p2 != null) {
            shape = new GeneralPath();

            // Crtanje isprekidane linije zavisnosti
            ((GeneralPath) shape).moveTo(p1.x, p1.y);
            ((GeneralPath) shape).lineTo(p2.x, p2.y);

            // Dodavanje strelice na kraju linije
            drawArrow((GeneralPath) shape, p1, p2);

        }
    }

    private void drawArrow(GeneralPath path, Point p1, Point p2) {
        // Izračunavanje parametara za strelicu
        int arrowLength = 15;
        int arrowWidth = 10;

        double angle = Math.atan2(p2.y - p1.y, p2.x - p1.x); // Ugao linije

        int x3 = p2.x - (int) (arrowLength * Math.cos(angle));
        int y3 = p2.y - (int) (arrowLength * Math.sin(angle));
        int x4 = x3 - (int) (arrowWidth * Math.cos(angle - Math.PI / 6));
        int y4 = y3 - (int) (arrowWidth * Math.sin(angle - Math.PI / 6));
        int x5 = x3 - (int) (arrowWidth * Math.cos(angle + Math.PI / 6));
        int y5 = y3 - (int) (arrowWidth * Math.sin(angle + Math.PI / 6));

        // Crtanje strelice kao poligona
        path.moveTo(p2.x, p2.y);
        path.lineTo(x4, y4);
        path.moveTo(p2.x, p2.y);
        path.lineTo(x5, y5);
    }

    private Point getCenter(Point p) {
        // Implementacija za dobijanje centra elementa
        return p; // Ovo je primer, prilagodite prema vašoj implementaciji
    }


    @Override
    public boolean elementAt(Point pos) {
        return false;
    }

}