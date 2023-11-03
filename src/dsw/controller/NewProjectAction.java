package dsw.controller;

import dsw.controller.popups.PopupAction;
import dsw.controller.tree.model.ClassyTreeItem;
import dsw.core.ApplicationFramework;
import dsw.core.logger.MessageType;
import dsw.observer.IPublisher;
import dsw.observer.ISubscriber;
import dsw.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewProjectAction extends AbstractClassyAction implements PopupAction, IPublisher {

    ISubscriber mainFrame;

    public NewProjectAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/add.png"));
        putValue(NAME, "New Project");
        putValue(SHORT_DESCRIPTION, "New Project");
    }


    @Override
    public void popupAction(Object component) {
        ClassyTreeItem selected = (ClassyTreeItem) component;
        MainFrame.getInstance().getClassyTree().addChild(selected);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = MainFrame.getInstance().getClassyTree().getSelectedNode();
        if (selected == null) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessageType.NODE_NOT_SELECTED);
            return;
        }
        MainFrame.getInstance().getClassyTree().addChild(selected);
        System.out.println("radi");
    }
}
