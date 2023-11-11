package dsw.controller;

import dsw.controller.tree.model.ClassyTreeItem;
import dsw.core.ApplicationFramework;
import dsw.core.logger.MessageType;
import dsw.repository.implementation.Diagram;
import dsw.repository.implementation.NodeType;
import dsw.repository.implementation.Package;
import dsw.repository.implementation.Project;
import dsw.repository.implementation.ProjectExplorer;
import dsw.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewPackageAction extends AbstractClassyAction {

    public NewPackageAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/addpackage.png"));
        putValue(NAME, "New Package");
        putValue(SHORT_DESCRIPTION, "New Package");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = MainFrame.getInstance().getClassyTree().getSelectedNode();
        if (selected == null) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessageType.NODE_NOT_SELECTED);
            return;
        }
        else if(MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode() instanceof ProjectExplorer)
        {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessageType.CANT_ADD_PACKAGE_IN_PROJECTEXPLORER);
            return;
        }

        else if(MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode() instanceof Diagram)
        {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessageType.CANT_ADD);
            return;
        }
        else if(MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode() instanceof Package)
        {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessageType.CANT_ADD);
            return;
        }

        MainFrame.getInstance().getClassyTree().addChild(selected, NodeType.Package);
    }
}
