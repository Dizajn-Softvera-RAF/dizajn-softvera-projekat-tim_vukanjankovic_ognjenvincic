package dsw.state;

import dsw.model.DiagramModel;
import dsw.model.elements.Interclass;
import dsw.model.helpers.ClickedValue;
import dsw.repository.implementation.Diagram;
import dsw.view.painters.ConnectionPainter;
import dsw.view.painters.InterclassPainter;

import java.awt.*;
import java.awt.geom.Line2D;

public class AbstractState {

    protected Point diagramPoint(Point p, DiagramModel model) {

        return new Point((int) ((p.x + Math.abs(model.getTransformX()))/ model.getZoom()), (int) ((p.y + Math.abs(model.getTransformY()))/ model.getZoom()));
    }

    protected ClickedValue getClickedIndex(Point p, Diagram diagram) {

        for (int i = diagram.getModel().getDiagramElements().size()-1; i >= 0; i--) {

            InterclassPainter device = diagram.getModel().getDiagramElements().get(i);

            if (device.elementAt(p)) {
                return new ClickedValue(device, i, 0);
            }


        }

        for (int i = 0; i < diagram.getModel().getVeze().size(); i++) {
            ConnectionPainter device = diagram.getModel().getVeze().get(i);
            if (Line2D.ptLineDist(device.getDevice().getPosition().x, device.getDevice().getPosition().y, device.getDevice().getSize().width, device.getDevice().getSize().height, p.x, p.y) < device.getDevice().getStrokeWidth()/2 + 2) {

                return new ClickedValue(device, i, 1);
            }
        }
        return null;

    }

    protected void clearSelection(Diagram diagram) {
        for (InterclassPainter element : diagram.getModel().getDiagramElements()) {
            element.getDevice().setSelected(false);
        }
        for (ConnectionPainter element : diagram.getModel().getVeze()) {
            element.getDevice().setSelected(false);
        }
    }

    protected Interclass getClosest(int selectedElement, Diagram diagram, int axis) {
        if (selectedElement == 0) return null;
        double min = calculateDistance(diagram.getModel().getDiagramElements().get(0).getDevice().getPosition(), diagram.getModel().getDiagramElements().get(selectedElement).getDevice().getPosition(), axis);
        InterclassPainter device = diagram.getModel().getDiagramElements().get(0);
        int i = 0;
        for (InterclassPainter d : diagram.getModel().getDiagramElements()) {
            if (i++ == selectedElement) continue;
            double distance = calculateDistance(d.getDevice().getPosition(), diagram.getModel().getDiagramElements().get(selectedElement).getDevice().getPosition(), axis);
            if (distance < min) {
                min = distance;
                device = d;
            }
        }
        return device.getDevice();
    }


    private double calculateDistance(Point p1, Point p2, int axis) {
        float xAxis = Math.abs(p1.x - p2.x);
        float yAxis = Math.abs(p1.y - p2.y);
        if (axis == -1) return xAxis < yAxis ? xAxis : yAxis;

        if (axis == 0) return xAxis < yAxis ? xAxis : 999999999;
        if (axis == 1) return xAxis > yAxis ? yAxis : 999999999;

        return 999999999;
    }

    protected void clearLine(Diagram diagram) {
        diagram.getModel().setTempLine(new Point(-1,-1), new Point(0,0));
    }
}
