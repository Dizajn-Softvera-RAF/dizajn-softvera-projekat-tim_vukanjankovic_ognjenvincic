package dsw.controller.listeners;

import dsw.controller.tree.ClassyTree;
import dsw.controller.tree.model.ClassyTreeItem;
import dsw.core.ApplicationFramework;
import dsw.core.logger.MessageType;
import dsw.observer.IPublisher;
import dsw.observer.ISubscriber;
import dsw.repository.implementation.Diagram;
import dsw.repository.implementation.Project;
import dsw.repository.implementation.ProjectExplorer;
import dsw.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PromenaNazivaAction implements ActionListener, IPublisher {

    JDialog jd;
    JTextField tf;

    public PromenaNazivaAction(JTextField textField, JDialog jDialog) {
        tf = textField;
        jd = jDialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = tf.getText();
        if (text.trim().equals("")) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessageType.EMPTY_FIELD);
            return;
        }
        ClassyTreeItem node = MainFrame.getInstance().getClassyTree().getSelectedNode();
        if (node == null || node.getClassyNode() instanceof ProjectExplorer) return;
        if (node.getClassyNode() instanceof Project) {
            ( node.getClassyNode()).setName(text.trim());
            notifySubscribers(node.getClassyNode());

            MainFrame.getInstance().getClassyTree().updateNodeName(node, text.trim());
        }
        if (node.getClassyNode() instanceof Diagram) {
            (node.getClassyNode()).setName(text.trim());
            notifySubscribers(node.getClassyNode().getParent());

            MainFrame.getInstance().getClassyTree().updateNodeName(node, text.trim());
        }
        jd.dispose();
    }

    @Override
    public void addSubscriber(ISubscriber sub) {

    }

    @Override
    public void removeSubscriber(ISubscriber sub) {

    }

    @Override
    public void notifySubscribers(Object notification) {
        MainFrame.getInstance().getProjectView().update(notification);
    }
}

