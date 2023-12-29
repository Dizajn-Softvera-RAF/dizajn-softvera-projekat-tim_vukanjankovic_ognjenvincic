package dsw.controller.listeners;

import dsw.core.ApplicationFramework;
import dsw.core.logger.MessageType;
import dsw.model.elements.Interclass;
import dsw.observer.IPublisher;
import dsw.observer.ISubscriber;
import dsw.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MetodaActionListener implements ActionListener, IPublisher {

    JTextField jTextField;
    Interclass interclass;
    JDialog jd;

    public MetodaActionListener(JTextField jt, Interclass interclass, JDialog jDialog) {
        this.interclass = interclass;
        jTextField = jt;
        jd = jDialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(jTextField.getText().trim().equals("")) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessageType.EMPTY_FIELD);
            return;
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