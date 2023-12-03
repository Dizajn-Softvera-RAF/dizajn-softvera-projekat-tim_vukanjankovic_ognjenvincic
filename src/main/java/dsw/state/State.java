package dsw.state;

import dsw.repository.implementation.Diagram;

import java.awt.event.MouseEvent;

public interface State {

    void mouseClicked(MouseEvent e, Diagram diagram);
    void mouseDragged(MouseEvent e, Diagram diagram);
    void mouseReleased(MouseEvent e, Diagram diagram);

}
