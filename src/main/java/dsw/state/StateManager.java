package dsw.state;

import dsw.model.DiagramModel;
import dsw.state.concrete.ConnectionState;
import dsw.state.concrete.DiagramState;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.Line2D;
@Getter
@Setter
public class StateManager {

    private State currentState;
    private DiagramState diagramState;
    private ConnectionState connectionState;

    public StateManager() {
        initStates();
    }

    private void initStates() {
        diagramState = new DiagramState();
        connectionState = new ConnectionState();

        currentState = diagramState;
    }

    public void setConnectionState(){
        currentState = connectionState;
    }
    public void setDiagramState(){
        currentState = diagramState;
    }
}
