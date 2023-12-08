package dsw.controller;

import dsw.controller.tree.model.ClassyTreeItem;
import dsw.core.ApplicationFramework;
import dsw.core.logger.MessageType;
import dsw.observer.IPublisher;
import dsw.observer.ISubscriber;
import dsw.repository.implementation.Diagram;
import dsw.repository.implementation.NodeType;
import dsw.repository.implementation.ProjectExplorer;
import dsw.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewDiagramAction extends AbstractClassyAction implements IPublisher {

    ISubscriber mainFrame;

    public NewDiagramAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/addDiagram.png"));
        putValue(NAME, "New Diagram");
        putValue(SHORT_DESCRIPTION, "New Diagram");

    }

    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = MainFrame.getInstance().getClassyTree().getSelectedNode();
        if (selected == null) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessageType.NODE_NOT_SELECTED);
            return;
        }
        else if(MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode() instanceof ProjectExplorer)
        {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessageType.CANT_ADD_DIAGRAM_IN_PROJECTEXPLORER);
            return;
        }

        else if(MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode() instanceof Diagram)
        {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessageType.CANT_ADD);
            return;
        }
        MainFrame.getInstance().getClassyTree().addChild(selected, NodeType.Diagram);
    }

    @Override
    public void addSubscriber(ISubscriber sub) {
        mainFrame = sub;
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {

    }

    @Override
    public void notifySubscribers(Object notification) {
        if (mainFrame == null) return;
        mainFrame.update(notification);
    }

}