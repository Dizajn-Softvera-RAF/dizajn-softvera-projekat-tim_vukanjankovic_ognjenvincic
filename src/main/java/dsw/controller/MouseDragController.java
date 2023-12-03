package dsw.controller;

import dsw.repository.implementation.Diagram;
import dsw.state.State;
import dsw.view.MainFrame;
import lombok.Getter;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseDragController extends MouseAdapter {
    private Diagram diagram;
    @Getter
    private Point startPoint;

    public MouseDragController(Diagram diagram) {
        this.diagram = diagram;
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        State current = MainFrame.getInstance().getProjectView().getStateManager().getCurrentState();
        current.mouseDragged(e, diagram);
    }
}
