package dsw.controller.listeners;

import dsw.controller.tree.model.ClassyTreeItem;
import dsw.core.ApplicationFramework;
import dsw.core.logger.MessageType;
import dsw.model.elements.Atribut;
import dsw.model.elements.DiagramElement;
import dsw.model.elements.Interclass;
import dsw.model.elements.Klasa;
import dsw.observer.IPublisher;
import dsw.observer.ISubscriber;
import dsw.repository.implementation.Diagram;
import dsw.repository.implementation.Project;
import dsw.state.concrete.KlasaState;
import dsw.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PipedReader;
import java.util.ArrayList;
import java.util.Locale;

public class AtributActionListener implements ActionListener, IPublisher {

    JTextField jTextField;
    JTextField jTextField1;
    JTextField jTextField2;
    DiagramElement interclass;
    JDialog jd;

    public AtributActionListener(JTextField jt,JTextField jt1, JTextField jt2,  DiagramElement interclass, JDialog jDialog) {
        this.interclass = interclass;
        jTextField = jt;
        jTextField1 = jt1;
        jTextField2 = jt2;
        jd = jDialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(jTextField.getText().trim().equals("")) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessageType.EMPTY_FIELD);
            return;
        }

        if(interclass instanceof Klasa){
            Atribut atribut = new Atribut(jTextField.getText().trim(), jTextField1.getText().trim(), jTextField2.getText().trim());
            ArrayList<Atribut> atributs = ((Klasa)interclass).getAtributs();
            atributs.add(atribut);
            ((Klasa)interclass).setAtributs(atributs);
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
