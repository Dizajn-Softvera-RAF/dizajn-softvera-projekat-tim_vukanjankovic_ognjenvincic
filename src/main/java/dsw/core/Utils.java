package dsw.core;

import dsw.model.DiagramModel;
import dsw.model.helpers.PointCustom;
import dsw.view.DiagramView;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.PathIterator;
import java.net.URL;
import java.util.ArrayList;

public class Utils {
    public static ImageIcon loadIcon(String fileName, int width, int height){

        URL imageURL = Utils.class.getResource(fileName);
        ImageIcon icon = null;

        if (imageURL != null) {
            icon = new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        }
        else {
            System.err.println("Resource not found: " + fileName);
        }
        return icon;
    }

    public static Point diagramPoint(Point p, DiagramModel model, double zoom) {

        return new Point((int) ((p.x + Math.abs(model.getTransformX()))/ zoom), (int) ((p.y + Math.abs(model.getTransformY()))/ zoom));
    }


    public static Point getTranslation(DiagramView dv, double prevZoom) {
        Point p = new Point();

        int originalWidth = dv.getSize().width;
        int originalHeight = dv.getSize().height;

        int zoomWidth1 = (int) (originalWidth * dv.getDiagram().getModel().getZoom());
        int zoomHeight1 = (int) (originalHeight * dv.getDiagram().getModel().getZoom());

        int zoomWidth2 = (int) (originalWidth * prevZoom);
        int zoomHeight2 = (int) (originalHeight * prevZoom);



        int centerX1 = zoomWidth1 / 2;
        int centerY1 = zoomHeight1 / 2;

        int centerX2 = zoomWidth2 / 2;
        int centerY2 = zoomHeight2 / 2;

        int offsetX = centerX1 - centerX2;
        int offsetY = centerY1 - centerY2;



        p.x = (int) (dv.getDiagram().getModel().getTransformX() - offsetX);
        p.y = (int) (dv.getDiagram().getModel().getTransformY() - offsetY);

        if (p.x > 0) p.x = 0;

        if (p.y > 0) p.y = 0;

        if (dv.getSize().getWidth() * (1 - dv.getDiagram().getModel().getZoom()) > p.x) {
            p.x = (int) (dv.getSize().getWidth() * (1 - dv.getDiagram().getModel().getZoom()));
        }
        if (dv.getSize().getHeight() * (1 - dv.getDiagram().getModel().getZoom()) > p.y) {
            p.y = (int) (dv.getSize().getHeight() * (1 - dv.getDiagram().getModel().getZoom()));
        }

        return p;

    }

    public static Point getTranslation(DiagramView dv, double prevZoom, Point e) {
        Point p = new Point();

        int originalWidth = dv.getSize().width;
        int originalHeight = dv.getSize().height;

        int zoomWidth2 = (int) (originalWidth * prevZoom);
        int zoomHeight2 = (int) (originalHeight * prevZoom);

        int centerX1 = diagramPoint(e, dv.getDiagram().getModel(), dv.getDiagram().getModel().getZoom()).x;
        int centerY1 = diagramPoint(e, dv.getDiagram().getModel(), dv.getDiagram().getModel().getZoom()).y;

        int centerX2 = zoomWidth2 / 2;
        int centerY2 = zoomHeight2 / 2;

        int offsetX = centerX1 - centerX2;
        int offsetY = centerY1 - centerY2;

        p.x = (int) (dv.getDiagram().getModel().getTransformX() - offsetX);
        p.y = (int) (dv.getDiagram().getModel().getTransformY() - offsetY);

        if (p.x > 0) p.x = 0;

        if (p.y > 0) p.y = 0;

        if (dv.getSize().getWidth() * (1 - dv.getDiagram().getModel().getZoom()) > p.x) {
            p.x = (int) (dv.getSize().getWidth() * (1 - dv.getDiagram().getModel().getZoom()));
        }
        if (dv.getSize().getHeight() * (1 - dv.getDiagram().getModel().getZoom()) > p.y) {
            p.y = (int) (dv.getSize().getHeight() * (1 - dv.getDiagram().getModel().getZoom()));
        }

        return p;

    }

    public static ArrayList<PointCustom> getShapeBounds(Shape s) {
        ArrayList<double[]> areaPoints = new ArrayList<>();
        ArrayList<Line2D.Double> areaSegments = new ArrayList<>();
        double[] coords = new double[6];

        for (PathIterator pi = s.getPathIterator(null); !pi.isDone(); pi.next()) {
            // The type will be SEG_LINETO, SEG_MOVETO, or SEG_CLOSE
            // Because the Area is composed of straight lines
            int type = pi.currentSegment(coords);
            // We record a double array of {segment type, x coord, y coord}
            double[] pathIteratorCoords = {type, coords[0], coords[1]};
            areaPoints.add(pathIteratorCoords);
        }

        double[] start = new double[3]; // To record where each polygon starts

        for (int i = 0; i < areaPoints.size(); i++) {
            // If we're not on the last point, return a line from this point to the next
            double[] currentElement = areaPoints.get(i);

            // We need a default value in case we've reached the end of the ArrayList
            double[] nextElement = {-1, -1, -1};
            if (i < areaPoints.size() - 1) {
                nextElement = areaPoints.get(i + 1);
            }

            // Make the lines
            if (currentElement[0] == PathIterator.SEG_MOVETO) {
                start = currentElement; // Record where the polygon started to close it later
            }

            if (nextElement[0] == PathIterator.SEG_LINETO) {
                areaSegments.add(
                        new Line2D.Double(
                                currentElement[1], currentElement[2],
                                nextElement[1], nextElement[2]
                        )
                );
            } else if (nextElement[0] == PathIterator.SEG_CLOSE) {
                areaSegments.add(
                        new Line2D.Double(
                                currentElement[1], currentElement[2],
                                start[1], start[2]
                        )
                );
            }
        }

        ArrayList<PointCustom> points = new ArrayList<>();
        for (Line2D.Double line : areaSegments) {
            points.add(new PointCustom(line.getX1(), line.getY1()));
            points.add(new PointCustom(line.getX2(), line.getY2()));
        }

        return points;
    }




}
