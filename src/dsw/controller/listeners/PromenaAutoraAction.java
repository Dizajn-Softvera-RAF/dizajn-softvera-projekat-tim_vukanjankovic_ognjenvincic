package dsw.controller.listeners;

import dsw.controller.tree.ClassyTree;
import dsw.controller.tree.model.ClassyTreeItem;
import dsw.core.ApplicationFramework;
import dsw.core.logger.MessageType;
import dsw.observer.IPublisher;
import dsw.observer.ISubscriber;
import dsw.repository.implementation.Project;
import dsw.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PromenaAutoraAction implements ActionListener, IPublisher {

    JTextField jTextField;
    ClassyTreeItem classy;
    JDialog jd;

    public PromenaAutoraAction(JTextField jt, ClassyTreeItem mapTreeItem, JDialog jDialog) {
        classy = mapTreeItem;
        jTextField = jt;
        jd = jDialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(jTextField.getText().trim().equals("")) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessageType.EMPTY_FIELD);
            return;
        }
        Project p = (Project) classy.getClassyNode();
        p.setAutor(jTextField.getText().trim());
        notifySubscribers(p);
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
