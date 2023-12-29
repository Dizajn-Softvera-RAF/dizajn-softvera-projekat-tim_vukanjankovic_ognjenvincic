package dsw.controller;

import dsw.core.ApplicationFramework;
import dsw.core.Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class UndoAction extends AbstractClassyAction{

    public UndoAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, Utils.loadIcon("/images/undo.png", 32, 32));
        putValue(NAME, "Undo");
        putValue(SHORT_DESCRIPTION, "Undo");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Undo");
        ApplicationFramework.getInstance().getGui().getCommandManager().undoCommand();

    }

}
