package dsw.controller;

import dsw.core.Utils;
import dsw.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewKlasaAction extends AbstractClassyAction {
    public NewKlasaAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, Utils.loadIcon("/images/class.png", 32, 32));
        putValue(NAME, "New mind map");
        putValue(SHORT_DESCRIPTION, "New mind map");

    }

    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().startKlasaState();
    }
}