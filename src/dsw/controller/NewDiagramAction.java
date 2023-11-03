package dsw.controller;

import dsw.controller.tree.model.ClassyTreeItem;
import dsw.core.ApplicationFramework;
import dsw.core.logger.MessageType;
import dsw.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewDiagramAction extends AbstractClassyAction {
    public NewDiagramAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/map-tool.png"));
        putValue(NAME, "New mind map");
        putValue(SHORT_DESCRIPTION, "New mind map");

    }

    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = MainFrame.getInstance().getClassyTree().getSelectedNode();
        if (selected == null) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessageType.NODE_NOT_SELECTED);
            return;
        }
        MainFrame.getInstance().getClassyTree().addChild(selected);
    }
}