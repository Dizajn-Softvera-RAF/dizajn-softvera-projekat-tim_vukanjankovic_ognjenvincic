package dsw.controller;

import dsw.view.MainFrame;
import dsw.view.modals.InfoModal;
import dsw.view.modals.Modal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class InfoAction extends AbstractClassyAction {
    public InfoAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/info.png"));
        putValue(NAME, "Info");
        putValue(SHORT_DESCRIPTION, "Info");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Modal infoModal = new InfoModal(MainFrame.getInstance());
        infoModal.showModal("Info");
    }
}
