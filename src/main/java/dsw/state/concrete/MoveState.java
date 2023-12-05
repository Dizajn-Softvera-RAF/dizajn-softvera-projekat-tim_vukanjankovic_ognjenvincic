package dsw.state.concrete;

import dsw.controller.AbstractClassyAction;
import dsw.core.ApplicationFramework;
import dsw.core.Config;
import dsw.model.elements.DiagramDevice;
import dsw.repository.implementation.Diagram;
import dsw.state.AbstractState;
import dsw.state.State;
import dsw.view.DiagramView;
import dsw.view.MainFrame;
import dsw.view.painters.DevicePainter;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MoveState extends AbstractState implements State {

    int selectedElement = -1;
    Point startPoint = new Point();
    @Override
    public void mouseClicked(MouseEvent e, Diagram diagram) {
        startPoint = mapPoint(e.getPoint(), diagram.getModel());
    }

    @Override
    public void mouseDragged(MouseEvent e, Diagram diagram) {
        Point point = mapPoint(e.getPoint(), diagram.getModel());

        if (diagram.getModel().getSelectedElements().size() == 0){
            int offsetX = (int) -(startPoint.x - point.getX());
            int offsetY = (int) -(startPoint.y - point.getY());

            double x = diagram.getModel().getTransformX()+offsetX*diagram.getModel().getZoom();
            double y = diagram.getModel().getTransformY()+offsetY*diagram.getModel().getZoom();

            DiagramView dv = (DiagramView) MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent();

            if (x <= 0 && dv.getSize().getWidth() * (1 - diagram.getModel().getZoom()) <= x) {
                dv.getDiagram().getModel().setTransformX(x);
            }
            if (y <= 0 && dv.getSize().getHeight() * (1 - diagram.getModel().getZoom()) <= y) {
                dv.getDiagram().getModel().setTransformY(y);
            }
            startPoint = mapPoint(e.getPoint(), diagram.getModel());

        }
        if (diagram.getModel().getSelectedElements().size() == 1) {
            selectedElement = diagram.getModel().getDiagramElements().indexOf(diagram.getModel().getSelectedElements().get(0));
            DiagramDevice closest = getClosest(selectedElement, diagram, -1);
            if (Config.SNAPPING) {

                diagram.getModel().setAlignmentLineX(new Point(-1, -1), false);
                diagram.getModel().setAlignmentLineY(new Point(-1, -1), false);

                if (closest == null) {
                    diagram.getModel().getDiagramElements().get(selectedElement).getDevice().setPosition(mapPoint(e.getPoint(), diagram.getModel()));
                    diagram.getModel().setAlignmentLineX(new Point(-1, -1), false);
                    diagram.getModel().setAlignmentLineY(new Point(-1, -1), false);
                    diagram.getModel().notifySubscribers(null);
                    return;
                }
                if (Math.abs(closest.getPosition().getY() - point.getY()) < 10) {

                    DiagramDevice closest2 = getClosest(selectedElement, diagram, 0);

                    if (closest2 != null && Math.abs(closest2.getPosition().getX() - point.getX()) < 10) {
                        Point p = mapPoint(e.getPoint(), diagram.getModel());
                        p.setLocation(closest2.getPosition().getX(), closest.getPosition().getY());

                        diagram.getModel().getDiagramElements().get(selectedElement).getDevice().setPosition(p);
                        diagram.getModel().setAlignmentLineY(new Point(-1, (int) Math.floor(closest.getPosition().getY())), false);
                        diagram.getModel().setAlignmentLineX(new Point((int) Math.floor(closest2.getPosition().getX()), -1), false);
                    } else {
                        Point p = mapPoint(e.getPoint(), diagram.getModel());
                        p.setLocation(p.getX(), closest.getPosition().getY());

                        diagram.getModel().getDiagramElements().get(selectedElement).getDevice().setPosition(p);
                        diagram.getModel().setAlignmentLineY(new Point(-1, (int) Math.floor(closest.getPosition().getY())), false);
                    }


                    diagram.getModel().notifySubscribers(null);
                    return;
                }
                if (Math.abs(closest.getPosition().getX() - point.getX()) < 10) {

                    DiagramDevice closest2 = getClosest(selectedElement, diagram, 1);

                    if (closest2 != null && Math.abs(closest2.getPosition().getY() - point.getY()) < 10) {
                        Point p = mapPoint(e.getPoint(), diagram.getModel());
                        p.setLocation(closest.getPosition().getX(), closest2.getPosition().getY());

                        diagram.getModel().getDiagramElements().get(selectedElement).getDevice().setPosition(p);
                        diagram.getModel().setAlignmentLineX(new Point((int) Math.floor(closest.getPosition().getX()), -1), false);
                        diagram.getModel().setAlignmentLineY(new Point(-1, (int) Math.floor(closest2.getPosition().getY())), false);
                    } else {
                        Point p = mapPoint(e.getPoint(), diagram.getModel());
                        p.setLocation(closest.getPosition().getX(), p.getY());

                        diagram.getModel().getDiagramElements().get(selectedElement).getDevice().setPosition(p);
                        diagram.getModel().setAlignmentLineX(new Point((int) Math.floor(closest.getPosition().getX()), -1), false);
                    }

                    diagram.getModel().notifySubscribers(null);
                    return;
                }
            }
            diagram.getModel().getDiagramElements().get(selectedElement).getDevice().setPosition(mapPoint(e.getPoint(), diagram.getModel()));
            diagram.getModel().setAlignmentLineX(new Point(-1, -1), false);
            diagram.getModel().setAlignmentLineY(new Point(-1, -1), false);
        } else {

            int offsetX = (int) -(startPoint.x - point.getX());
            int offsetY = (int) -(startPoint.y - point.getY());
            for (DevicePainter d : diagram.getModel().getSelectedElements()) {

                d.getDevice().setPosition(new Point(d.getDevice().getPosition().x + offsetX, d.getDevice().getPosition().y + offsetY));
            }
            startPoint = mapPoint(e.getPoint(), diagram.getModel());

        }
        diagram.getModel().notifySubscribers(null);
    }

    @Override
    public void mouseReleased(MouseEvent e, Diagram diagram) {
        diagram.getModel().setAlignmentLineX(new Point(-1, -1), true);
        diagram.getModel().setAlignmentLineY(new Point(-1, -1), true);

    }

}
