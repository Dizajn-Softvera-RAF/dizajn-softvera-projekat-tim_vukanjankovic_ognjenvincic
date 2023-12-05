package dsw.controller;

import dsw.core.Utils;
import dsw.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewEnumAction extends AbstractClassyAction{
    public NewEnumAction() {
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
            putValue(SMALL_ICON, Utils.loadIcon("/images/enum.png", 32, 32));
            putValue(NAME, "Enum");
            putValue(SHORT_DESCRIPTION, "Enum");
        }


    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().startEnumState();
    }
}
