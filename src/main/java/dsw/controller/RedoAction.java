package dsw.controller;

import dsw.core.ApplicationFramework;
import dsw.core.Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class RedoAction extends AbstractClassyAction{

    public RedoAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, Utils.loadIcon("/images/redo.png", 32, 32));
        putValue(NAME, "Redo");
        putValue(SHORT_DESCRIPTION, "Redo");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ApplicationFramework.getInstance().getGui().getCommandManager().doCommand();

    }
}