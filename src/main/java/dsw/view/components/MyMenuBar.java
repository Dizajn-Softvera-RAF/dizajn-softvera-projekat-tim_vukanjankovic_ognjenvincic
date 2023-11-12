package dsw.view.components;

import dsw.view.MainFrame;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MyMenuBar extends JMenuBar {
    public MyMenuBar() {
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        fileMenu.add(MainFrame.getInstance().getActionManager().getNewProjectAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getExitAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getRenameAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getDeleteAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getNewPackageAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getNewDiagramAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getAutorAction());

        JMenu about = new JMenu("About");
        about.setMnemonic(KeyEvent.VK_A);
        about.add(MainFrame.getInstance().getActionManager().getInfoAction());

        add(fileMenu);
        add(about);
    }

}