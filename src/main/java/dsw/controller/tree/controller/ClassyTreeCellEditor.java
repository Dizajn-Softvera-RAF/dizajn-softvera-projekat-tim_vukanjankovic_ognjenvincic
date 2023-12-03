package dsw.controller.tree.controller;

import dsw.controller.tree.model.ClassyTreeItem;
import dsw.controller.tree.view.ClassyTreeView;
import dsw.core.ApplicationFramework;
import dsw.core.logger.MessageType;
import dsw.observer.IPublisher;
import dsw.observer.ISubscriber;
import dsw.repository.implementation.Diagram;
import dsw.repository.implementation.Project;
import dsw.view.MainFrame;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class ClassyTreeCellEditor extends DefaultTreeCellEditor implements ActionListener, IPublisher {

    private Object clickedOn = null;
    private JTextField edit = null;

    public ClassyTreeCellEditor(ClassyTreeView arg1, DefaultTreeCellRenderer arg2) {
        super(arg1, arg2);
    }

    public Component getTreeCellEditorComponent(JTree arg0, Object arg1, boolean arg2, boolean arg3, boolean arg4, int arg5) {
        clickedOn =arg1;
        edit=new JTextField(arg1.toString());
        edit.addActionListener(this);
        return edit;
    }

    public boolean isCellEditable(EventObject arg0) {
        if (arg0 instanceof MouseEvent && ((MouseEvent)arg0).getClickCount()==3) return true;
        return false;
    }

    public void actionPerformed(ActionEvent e){

        if (!(clickedOn instanceof ClassyTreeItem))
            return;

        ClassyTreeItem clicked = (ClassyTreeItem) clickedOn;
        if (e.getActionCommand().trim().equals("")) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessageType.EMPTY_FIELD);
            return;
        }
        clicked.setName(e.getActionCommand().trim());

        if (clicked.getClassyNode() instanceof Project) {
            notifySubscribers(clicked.getClassyNode());
        }
        if (clicked.getClassyNode() instanceof Diagram) {
            notifySubscribers(clicked.getClassyNode().getParent());
        }
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
