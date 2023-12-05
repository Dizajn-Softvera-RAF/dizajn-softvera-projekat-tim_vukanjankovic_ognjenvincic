package dsw.controller;

import dsw.core.Utils;
import dsw.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewAgregacijaAction extends AbstractClassyAction{
    public NewAgregacijaAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, Utils.loadIcon("/images/agregation.png", 32, 32));
        putValue(NAME, "New Connection");
        putValue(SHORT_DESCRIPTION, "New Connection");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().startAgregacijaState();
    }
}
