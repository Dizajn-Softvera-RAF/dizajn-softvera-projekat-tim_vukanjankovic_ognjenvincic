package dsw.state.concrete;

import dsw.model.DiagramModel;
import dsw.model.helpers.ClickedValue;
import dsw.repository.implementation.Diagram;
import dsw.state.AbstractState;
import dsw.state.State;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ZavisnostState extends AbstractState implements State{
    Point startPoint = new Point();

    @Override
    public void mouseClicked(MouseEvent e, Diagram diagram) {
        startPoint = diagramPoint(e.getPoint(), diagram.getModel());
    }

    @Override
    public void mouseDragged(MouseEvent e, Diagram diagram) {
        diagram.getModel().setTempLine(startPoint, diagramPoint(e.getPoint(), diagram.getModel()));
    }

    @Override
    public void mouseReleased(MouseEvent e, Diagram diagram) {
        clearSelection(diagram);
        ClickedValue start = getClickedIndex(startPoint, diagram);
        ClickedValue end = getClickedIndex(diagramPoint(e.getPoint(), diagram.getModel()), diagram);
        if (start == null || end == null) {
            startPoint = new Point(-1,-1);
            clearLine(diagram);
            return;
        }

        diagram.getModel().addConnection(new Point(start.getIndex(), end.getIndex()));

        clearLine(diagram);
    }
}
