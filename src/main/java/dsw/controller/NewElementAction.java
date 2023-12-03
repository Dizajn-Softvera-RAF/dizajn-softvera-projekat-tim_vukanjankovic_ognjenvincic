package dsw.controller;

import dsw.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewElementAction extends AbstractClassyAction {
    public NewElementAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/diagram.png"));
        putValue(NAME, "New mind map");
        putValue(SHORT_DESCRIPTION, "New mind map");

    }

    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().startDiagramState();
    }
}