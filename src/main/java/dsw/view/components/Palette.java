package dsw.view.components;

import dsw.controller.popups.PopupType;
import dsw.core.Config;
import dsw.state.State;
import dsw.state.concrete.*;
import dsw.view.DiagramView;
import dsw.view.MainFrame;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Palette extends JToolBar {

    private JButton klasaButton, interfejsButton, enumButton, agregacijaButton, kompozicijaButton, zavisnostButton, generalizacijaButton;
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

        enumButton = new JButton(MainFrame.getInstance().getActionManager().getEnumAction());
        klasaButton = new JButton(MainFrame.getInstance().getActionManager().getKlasaAction());
        interfejsButton = new JButton(MainFrame.getInstance().getActionManager().getInterfejsAction());
        agregacijaButton = new JButton(MainFrame.getInstance().getActionManager().getAgregacijaAction());
        kompozicijaButton = new JButton(MainFrame.getInstance().getActionManager().getKompozicijaAction());
        zavisnostButton = new JButton(MainFrame.getInstance().getActionManager().getZavisnostAction());
        generalizacijaButton = new JButton(MainFrame.getInstance().getActionManager().getGeneralizacijaAction());


        addElement(enumButton);
        addElement(klasaButton);
        addElement(interfejsButton);
        addElement(agregacijaButton);
        addElement(kompozicijaButton);
        addElement(zavisnostButton);
        addElement(generalizacijaButton);

        wrapper.add(row1);
        wrapper.add(Box.createRigidArea(new Dimension(5, 0)));
        add(wrapper);

    }

    public void setSelectedButton(State state){
        DiagramView mv = (DiagramView) MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent();
        reset();
        if (state instanceof KlasaState) {
            if (mv!=null) mv.setCursor(new Cursor(Cursor.HAND_CURSOR));
            klasaButton.setBorder(Config.palleteSelectedBorder);
        }
        if (state instanceof InterfejsState) {
            if (mv!=null) mv.setCursor(new Cursor(Cursor.HAND_CURSOR));
            interfejsButton.setBorder(Config.palleteSelectedBorder);
        }
        if (state instanceof EnumState) {
            if (mv!=null) mv.setCursor(new Cursor(Cursor.HAND_CURSOR));
            enumButton.setBorder(Config.palleteSelectedBorder);
        }
        if (state instanceof AgregacijaState) {
            if (mv!=null) mv.setCursor(new Cursor(Cursor.HAND_CURSOR));
            agregacijaButton.setBorder(Config.palleteSelectedBorder);
        }
        if (state instanceof KompozicijaState) {
            if (mv!=null) mv.setCursor(new Cursor(Cursor.HAND_CURSOR));
            kompozicijaButton.setBorder(Config.palleteSelectedBorder);
        }
        if (state instanceof ZavisnostState) {
            if (mv!=null) mv.setCursor(new Cursor(Cursor.HAND_CURSOR));
            zavisnostButton.setBorder(Config.palleteSelectedBorder);
        }
        if (state instanceof GeneralizacijaState) {
            if (mv!=null) mv.setCursor(new Cursor(Cursor.HAND_CURSOR));
            generalizacijaButton.setBorder(Config.palleteSelectedBorder);
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
        klasaButton.setBorder(raisedBorder);
        interfejsButton.setBorder(raisedBorder);
        enumButton.setBorder(raisedBorder);
        agregacijaButton.setBorder(raisedBorder);
        kompozicijaButton.setBorder(raisedBorder);
        zavisnostButton.setBorder(raisedBorder);
        generalizacijaButton.setBorder(raisedBorder);
    }
}

