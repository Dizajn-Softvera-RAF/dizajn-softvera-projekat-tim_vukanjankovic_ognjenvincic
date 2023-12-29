package dsw.controller;

import dsw.model.elements.DiagramElement;
import dsw.model.elements.Interclass;
import dsw.view.DiagramView;
import dsw.view.MainFrame;
import dsw.view.modals.AtributModal;
import dsw.view.modals.Modal;
import dsw.view.painters.InterclassPainter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AtributAction extends AbstractClassyAction{

    public AtributAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/autor.png"));
        putValue(NAME, "Edit autor");
        putValue(SHORT_DESCRIPTION, "Edit autor");

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        DiagramView dv = (DiagramView) MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent();

        DiagramElement selected = dv.getDiagram().getModel().getSelectedElements().get(0).getDevice();

        Modal atributModal = new AtributModal(MainFrame.getInstance(), selected);
        atributModal.showModal("Atribut");
    }
}
