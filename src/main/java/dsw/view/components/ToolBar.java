package dsw.view.components;

import dsw.view.MainFrame;

import javax.swing.*;

public class ToolBar extends JToolBar {
    public ToolBar() {
        super(HORIZONTAL);
        setFloatable(false);

        add(MainFrame.getInstance().getActionManager().getNewProjectAction());
        add(MainFrame.getInstance().getActionManager().getNewPackageAction());
        add(MainFrame.getInstance().getActionManager().getNewDiagramAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getRenameAction());
        add(MainFrame.getInstance().getActionManager().getDeleteAction());
        add(MainFrame.getInstance().getActionManager().getAutorAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getExitAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getSaveAction());
        add(MainFrame.getInstance().getActionManager().getSaveAsAction());
    }
}
