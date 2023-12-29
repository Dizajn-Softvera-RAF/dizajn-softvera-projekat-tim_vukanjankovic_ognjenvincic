package dsw.state.concrete;

import dsw.core.Config;
import dsw.core.Utils;
import dsw.model.DiagramModel;
import dsw.model.helpers.ClickedValue;
import dsw.model.helpers.PointCustom;
import dsw.model.helpers.SelectionTool;
import dsw.repository.implementation.Diagram;
import dsw.state.AbstractState;
import dsw.state.State;
import dsw.view.painters.ConnectionPainter;
import dsw.view.painters.InterclassPainter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class SelectionState extends AbstractState implements State {

    Point startPoint = new Point();
    Point endPoint = new Point();
    @Override
    public void mouseClicked(MouseEvent e, Diagram diagram) {
        if ((e.getModifiers() & ActionEvent.CTRL_MASK) != ActionEvent.CTRL_MASK) {
            diagram.getModel().clearSelecterElements();
        }

        ClickedValue clicked = getClickedIndex(diagramPoint(e.getPoint(), diagram.getModel()), diagram);
        if (clicked != null && clicked.getD() instanceof InterclassPainter) {
            InterclassPainter device = (InterclassPainter) clicked.getD();
            int index = clicked.getIndex();
            if (clicked.getType() == 0) {
                if (diagram.getModel().getSelectedElements().contains(device)) {
                    diagram.getModel().getSelectedElements().remove(device);
                } else {
                    diagram.getModel().addSelectedElements(device);
                }
                InterclassPainter temp = diagram.getModel().getDiagramElements().get(diagram.getModel().getDiagramElements().size()-1);
                diagram.getModel().getDiagramElements().set(diagram.getModel().getDiagramElements().size()-1, device);
                diagram.getModel().getDiagramElements().set(index, temp);
            } else {
                diagram.getModel().getVeze().get(index).getDevice().setSelected( !diagram.getModel().getVeze().get(index).getDevice().isSelected() );
            }

            diagram.getModel().notifySubscribers(null);
        }else if(clicked != null && clicked.getD() instanceof ConnectionPainter){
            ConnectionPainter device = (ConnectionPainter) clicked.getD();
            int index = clicked.getIndex();
            if (clicked.getType() == 0) {
                if (diagram.getModel().getSelectedElements().contains(device)) {
                    diagram.getModel().getSelectedElements().remove(device);
                } else {
                    diagram.getModel().addSelectedElements(device);
                }
                ConnectionPainter temp = diagram.getModel().getVeze().get(diagram.getModel().getDiagramElements().size()-1);
                diagram.getModel().getVeze().set(diagram.getModel().getDiagramElements().size()-1, device);
                diagram.getModel().getVeze().set(index, temp);
            } else {
                diagram.getModel().getVeze().get(index).getDevice().setSelected( !diagram.getModel().getVeze().get(index).getDevice().isSelected() );
            }

            diagram.getModel().notifySubscribers(null);
        }
        if (Config.SELECTION_TOOL == SelectionTool.POLYGON) {
            Point p = diagramPoint(e.getPoint(), diagram.getModel());

            diagram.getModel().setPolygon(new Polygon());
            diagram.getModel().getPolygon().addPoint(p.x, p.y);
            return;
        }

        if (Config.SELECTION_TOOL == SelectionTool.RECTANGLE) {
            startPoint = diagramPoint(e.getPoint(), diagram.getModel());
            diagram.getModel().notifySubscribers(null);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e, Diagram diagram) {

        if ((e.getModifiers() & ActionEvent.CTRL_MASK) == ActionEvent.CTRL_MASK)return;

        if (Config.SELECTION_TOOL == SelectionTool.POLYGON) {
            Point p = diagramPoint(e.getPoint(), diagram.getModel());
            diagram.getModel().getPolygon().addPoint(p.x, p.y);
            diagram.getModel().notifySubscribers(null);
            return;
        }

        if (Config.SELECTION_TOOL == SelectionTool.RECTANGLE) {
            Point p = diagramPoint(e.getPoint(), diagram.getModel());

            int px = (int) Math.min(startPoint.x,p.getX());
            int py = (int) Math.min(startPoint.y,p.getY());
            int pw= (int) Math.abs(startPoint.x-p.getX());
            int ph= (int) Math.abs(startPoint.y-p.getY());

            diagram.getModel().setSelectionLine(new Point(px, py), new Dimension(pw, ph));
        }
    }


    @Override
    public void mouseReleased(MouseEvent e, Diagram diagram) {
        if ((e.getModifiers() & ActionEvent.CTRL_MASK) == ActionEvent.CTRL_MASK)return;
        if (Config.SELECTION_TOOL == SelectionTool.POLYGON) {
            if (diagram.getModel().getPolygon() != null && diagram.getModel().getPolygon().npoints < 3) {
                diagram.getModel().setPolygon(null);
                diagram.getModel().notifySubscribers(null);
                return;
            }
            diagram.getModel().clearSelecterElements();
            for (InterclassPainter d : diagram.getModel().getDiagramElements()) {
                if (isInside(d, diagram.getModel())) {
                    diagram.getModel().addSelectedElements(d);
                }
            }

            diagram.getModel().setPolygon(null);
            diagram.getModel().notifySubscribers(null);

            return;
        }

        if (Config.SELECTION_TOOL == SelectionTool.RECTANGLE) {
            endPoint = diagramPoint(e.getPoint(), diagram.getModel());
            if (Math.abs(startPoint.x-endPoint.x) < 2 && Math.abs(startPoint.y-endPoint.y) < 2) {
                diagram.getModel().setSelectionLine(new Point(-1, -1), new Dimension(1,1));
                diagram.getModel().notifySubscribers(null);
                return;
            }
            diagram.getModel().clearSelecterElements();

            for(InterclassPainter d : diagram.getModel().getDiagramElements()){

                if(isInside(d, diagram.getModel())) diagram.getModel().addSelectedElements(d);

            }
            diagram.getModel().setSelectionLine(new Point(-1, -1), new Dimension(1,1));
            diagram.getModel().notifySubscribers(null);
        }
    }

    public boolean isInside(InterclassPainter d1, DiagramModel diagramModel){

        if (Config.SELECTION_TOOL == SelectionTool.RECTANGLE) {
            int x2 = d1.getDevice().getPosition().x;
            int y2 = d1.getDevice().getPosition().y;
            int w2 = d1.getDevice().getDimension().width;
            int h2 = d1.getDevice().getDimension().height;

            x2 = (int) ((x2-w2/2) - d1.getDevice().getStrokeWidth()/2);
            y2 = (int) ((y2-h2/2) - d1.getDevice().getStrokeWidth()/2);
            w2 = (int) (w2 + d1.getDevice().getStrokeWidth());
            h2 = (int) (h2 + d1.getDevice().getStrokeWidth());

            diagramModel.setSelectionDebug(new Point(x2, y2), new Dimension(w2, h2));

            Shape selection = diagramModel.getSelectionLine().getShape();

            if(selection.contains(x2 , y2 , w2 , h2 )) return true;

            return false;
        }
        if (Config.SELECTION_TOOL == SelectionTool.POLYGON) {
            ArrayList<PointCustom> bounds = Utils.getShapeBounds(d1.getShape());
            boolean inside = true;
            for(PointCustom p : bounds) {
                if(!diagramModel.getPolygon().contains(p.x, p.y)) {
                    inside = false;
                    break;
                }
            }
            return inside;
        }

        return false;
    }


}
