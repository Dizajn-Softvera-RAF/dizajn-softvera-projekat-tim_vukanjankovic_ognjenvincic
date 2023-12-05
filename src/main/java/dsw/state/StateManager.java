package dsw.state;

import dsw.model.DiagramModel;
import dsw.state.concrete.*;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.Line2D;
@Getter
@Setter
public class StateManager {

    private State currentState;
    private KlasaState klasaState;
    private InterfejsState interfejsState;
    private EnumState enumState;
    private AgregacijaState agregacijaState;
    private KompozicijaState kompozicijaState;
    private ZavisnostState zavisnostState;
    private GeneralizacijaState generalizacijaState;
    private SelectionState selectionState;
    private MoveState moveState;

    public StateManager() {
        initStates();
    }

    private void initStates() {
        agregacijaState = new AgregacijaState();
        kompozicijaState = new KompozicijaState();
        zavisnostState = new ZavisnostState();
        generalizacijaState = new GeneralizacijaState();
        klasaState = new KlasaState();
        interfejsState = new InterfejsState();
        enumState = new EnumState();
        selectionState = new SelectionState();
        moveState = new MoveState();

        currentState = klasaState;
    }

    public void setAgregacijaState(){
        currentState = agregacijaState;
    }
    public void setKompozicijaState(){
        currentState = kompozicijaState;
    }
    public void setZavisnostState(){
        currentState = zavisnostState;
    }
    public void setGeneralizacijaState(){
        currentState = generalizacijaState;
    }

    public void setKlasaState(){
        currentState = klasaState;
    }
    public void setInterfejsState(){
        currentState = interfejsState;
    }
    public void setEnumState(){
        currentState = enumState;
    }
    public void setSelectionState(){currentState = selectionState;}
    public void setMoveState(){currentState = moveState;}
}
