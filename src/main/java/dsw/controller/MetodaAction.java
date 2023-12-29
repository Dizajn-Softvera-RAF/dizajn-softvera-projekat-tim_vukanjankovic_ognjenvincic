package dsw.controller;

import dsw.model.elements.Interclass;
import dsw.view.MainFrame;
import dsw.view.modals.AtributModal;
import dsw.view.modals.MetodaModal;
import dsw.view.modals.Modal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class MetodaAction extends AbstractClassyAction{

    public MetodaAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/autor.png"));
        putValue(NAME, "Edit autor");
        putValue(SHORT_DESCRIPTION, "Edit autor");

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        Interclass selected = null;


        Modal metodaModal = new MetodaModal(MainFrame.getInstance(), selected);
        metodaModal.showModal("Metoda");
    }

}
