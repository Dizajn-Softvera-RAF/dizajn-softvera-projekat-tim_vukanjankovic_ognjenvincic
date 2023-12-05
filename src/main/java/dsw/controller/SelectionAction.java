package dsw.controller;

import dsw.core.Utils;
import dsw.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class SelectionAction extends AbstractClassyAction {
    public SelectionAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, Utils.loadIcon("/images/selection.png", 32, 32));
        putValue(NAME, "Selection tool");
        putValue(SHORT_DESCRIPTION, "Selection tool");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().startSelectionState();
    }


}
