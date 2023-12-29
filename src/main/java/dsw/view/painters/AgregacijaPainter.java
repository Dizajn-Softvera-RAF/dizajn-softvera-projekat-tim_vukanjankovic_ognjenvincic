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
    @Override
    public void updateShape() {
        Point p1 = getPoint1(); // Centar početnog elementa
        Point p2 = getPoint2(); // Centar krajnjeg elementa

        if (p1 != null && p2 != null) {
            shape = new GeneralPath();

            double angle = Math.atan2(p2.y - p1.y, p2.x - p1.x); // Ugao linije

            // Veličina romba
            int diamondSize = 10;

            // Pomeraj unazad duž linije
            double backwardShift = diamondSize / Math.sqrt(2); // Polovina dužine dijagonale romba

            // Novi p2 koji je pomeren unazad
            int p2xShifted = p2.x - (int) (backwardShift * Math.cos(angle));
            int p2yShifted = p2.y - (int) (backwardShift * Math.sin(angle));

            // Izračunavanje tačaka za romb
            int xLeft = p2xShifted - (int) (diamondSize * Math.cos(angle + Math.PI / 2));
            int yLeft = p2yShifted - (int) (diamondSize * Math.sin(angle + Math.PI / 2));
            int xRight = p2xShifted - (int) (diamondSize * Math.cos(angle - Math.PI / 2));
            int yRight = p2yShifted - (int) (diamondSize * Math.sin(angle - Math.PI / 2));
            int xTop = p2xShifted - (int) (diamondSize * Math.cos(angle));
            int yTop = p2yShifted - (int) (diamondSize * Math.sin(angle));
            int xBottom = p2xShifted + (int) (diamondSize * Math.cos(angle));
            int yBottom = p2yShifted + (int) (diamondSize * Math.sin(angle));

            // Crtanje linije do vrha romba
            ((GeneralPath) shape).moveTo(p1.x, p1.y);
            ((GeneralPath) shape).lineTo(xTop, yTop);

            // Crtanje romba kao poligona
            ((GeneralPath) shape).moveTo(xTop, yTop);
            ((GeneralPath) shape).lineTo(xLeft, yLeft);
            ((GeneralPath) shape).lineTo(xBottom, yBottom);
            ((GeneralPath) shape).lineTo(xRight, yRight);
            ((GeneralPath) shape).closePath();
        }
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
