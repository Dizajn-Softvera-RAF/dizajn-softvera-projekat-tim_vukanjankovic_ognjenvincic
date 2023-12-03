package dsw.view.components;

import dsw.controller.popups.PopupType;
import dsw.core.Config;
import dsw.state.State;
import dsw.state.concrete.ConnectionState;
import dsw.state.concrete.DiagramState;
import dsw.view.DiagramView;
import dsw.view.MainFrame;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Palette extends JToolBar {

    private JButton diagramButton, connectionButton;
    private JPanel row1, wrapper;
    public Palette() {
        super(VERTICAL);
        setFloatable(false);
        wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.X_AXIS));

        row1 = new JPanel();
        row1.setLayout(new BoxLayout(row1, BoxLayout.Y_AXIS));
        row1.setAlignmentY(Component.TOP_ALIGNMENT);
        wrapper.setAlignmentY(Component.TOP_ALIGNMENT);

        setSize(Integer.MAX_VALUE,  Integer.MAX_VALUE);

        diagramButton = new JButton(MainFrame.getInstance().getActionManager().getElementAction());
        connectionButton = new JButton(MainFrame.getInstance().getActionManager().getConnectionAction());

        addElement(diagramButton);
        addElement(connectionButton);

        wrapper.add(row1);
        wrapper.add(Box.createRigidArea(new Dimension(5, 0)));
        add(wrapper);

    }

    public void setSelectedButton(State state){
        DiagramView mv = (DiagramView) MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent();
        reset();
        if (state instanceof DiagramState) {
            if (mv!=null) mv.setCursor(new Cursor(Cursor.HAND_CURSOR));
            diagramButton.setBorder(Config.palleteSelectedBorder);
        }
        if (state instanceof ConnectionState) {
            if (mv!=null) mv.setCursor(new Cursor(Cursor.HAND_CURSOR));
            connectionButton.setBorder(Config.palleteSelectedBorder);
        }
    }

    private void addElement(JButton button) {
        button.setText("");
        button.setAlignmentY(Component.TOP_ALIGNMENT);
        row1.add(button);
        row1.add(Box.createRigidArea(new Dimension(0, 5)));

    }

    private void reset(){
        Border raisedBorder = Config.palleteDeselectedBorder;
        diagramButton.setBorder(raisedBorder);
        connectionButton.setBorder(raisedBorder);

    }
}

