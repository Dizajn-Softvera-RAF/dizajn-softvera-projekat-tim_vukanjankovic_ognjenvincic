package dsw.controller;

import dsw.core.Utils;
import dsw.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewInterfejsAction extends AbstractClassyAction{
    public NewInterfejsAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, Utils.loadIcon("/images/interface.png", 32, 32));
        putValue(NAME, "Interface");
        putValue(SHORT_DESCRIPTION, "Interface");
    }

    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().startInterfejsState();
    }
}

