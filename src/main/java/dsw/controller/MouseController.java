package dsw.controller;

import dsw.repository.implementation.Diagram;
import dsw.state.State;
import dsw.view.MainFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseController extends MouseAdapter {
    private Diagram diagram;

    public MouseController(Diagram diagram) {
        this.diagram = diagram;
    }
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            State current = MainFrame.getInstance().getProjectView().getStateManager().getCurrentState();
            current.mouseClicked(e, diagram);
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        State current = MainFrame.getInstance().getProjectView().getStateManager().getCurrentState();
        current.mouseReleased(e, diagram);
    }
}
