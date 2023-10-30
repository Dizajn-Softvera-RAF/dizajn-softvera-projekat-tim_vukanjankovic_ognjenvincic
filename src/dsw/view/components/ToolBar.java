package dsw.view.components;

import dsw.view.MainFrame;

import javax.swing.*;

public class ToolBar extends JToolBar {
    public ToolBar() {
        super(HORIZONTAL);
        setFloatable(false);

        add(MainFrame.getInstance().getActionManager().getExitAction());
    }
}
