package dsw.state.concrete;

import dsw.model.DiagramModel;
import dsw.model.helpers.ClickedValue;
import dsw.repository.implementation.Diagram;
import dsw.state.AbstractState;
import dsw.state.State;

import java.awt.*;
import java.awt.event.MouseEvent;

public class AgregacijaState extends AbstractState implements State {
    Point startPoint = new Point();

    @Override
    public void mouseClicked(MouseEvent e, Diagram diagram) {
        startPoint = mapPoint(e.getPoint(), diagram.getModel());
    }

    @Override
    public void mouseDragged(MouseEvent e, Diagram diagram) {
        diagram.getModel().setTempLine(startPoint,mapPoint(e.getPoint(), diagram.getModel()));
    }

    @Override
    public void mouseReleased(MouseEvent e, Diagram diagram) {
        clearSelection(diagram);
        DiagramModel oldModel = diagram.getModel().getClone();
        ClickedValue start = getClickedIndex(startPoint, diagram);
        ClickedValue end = getClickedIndex(mapPoint(e.getPoint(), diagram.getModel()), diagram);
        if (start == null || end == null) {
            startPoint = new Point(-1, -1);
            clearLine(diagram);
            return;
        }

        diagram.getModel().addConnection(new Point(start.getIndex(), end.getIndex()));

        clearLine(diagram);
    }
}
