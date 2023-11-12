package dsw.view;

import dsw.observer.ISubscriber;
import dsw.repository.implementation.Diagram;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class DiagramView extends JPanel implements ISubscriber {

    private Diagram diagram;

    public DiagramView(Diagram diagram){
        this.diagram = diagram;

        setName(diagram.getName());
//        setCursor(new Cursor(Cursor.HAND_CURSOR));

        diagram.getModel().addSubscriber(this);

        setBackground(Color.WHITE);

    }

    @Override
    public void update(Object notification) {
        repaint();
    }
}
