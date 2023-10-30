package dsw.view.components;

import dsw.view.MainFrame;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MyMenuBar extends JMenuBar {
    public MyMenuBar() {
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        fileMenu.add(MainFrame.getInstance().getActionManager().getExitAction());

        JMenu about = new JMenu("About");
        about.setMnemonic(KeyEvent.VK_A);
        about.add(MainFrame.getInstance().getActionManager().getInfoAction());

        add(fileMenu);
        add(about);
    }

}