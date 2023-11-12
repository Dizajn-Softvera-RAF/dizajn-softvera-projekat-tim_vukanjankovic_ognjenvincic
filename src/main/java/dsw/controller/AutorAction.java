package dsw.controller;

import dsw.controller.popups.PopupAction;
import dsw.controller.tree.model.ClassyTreeItem;
import dsw.view.modals.AutorModal;
import dsw.view.modals.Modal;
import dsw.core.ApplicationFramework;
import dsw.core.logger.MessageType;
import dsw.repository.implementation.Diagram;
import dsw.repository.implementation.Package;
import dsw.repository.implementation.ProjectExplorer;
import dsw.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AutorAction extends AbstractClassyAction implements PopupAction {

    public AutorAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/autor.png"));
        putValue(NAME, "Edit autor");
        putValue(SHORT_DESCRIPTION, "Edit autor");

    }


    @Override
    public void popupAction(Object component) {
        ClassyTreeItem selected = (ClassyTreeItem) component;

        if (selected.getClassyNode() instanceof Package) selected = (ClassyTreeItem) selected.getParent();
        if (selected.getClassyNode() instanceof Diagram) selected = (ClassyTreeItem) selected.getParent();

        Modal autorModal = new AutorModal(MainFrame.getInstance(), selected);
        autorModal.showModal("Promena autora");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = MainFrame.getInstance().getClassyTree().getSelectedNode();

        if(selected == null || selected.getClassyNode() instanceof ProjectExplorer) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessageType.NODE_NOT_SELECTED);
            return;
        }
        if (selected.getClassyNode() instanceof Package) selected = (ClassyTreeItem) selected.getParent();
        if (selected.getClassyNode() instanceof Diagram) selected = (ClassyTreeItem) selected.getParent();

        if (!MainFrame.getInstance().getClassyTree().projectExists(selected)) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessageType.PROJECT_DOESNT_EXIST);
            return;
        }
        Modal autorModal = new AutorModal(MainFrame.getInstance(), selected);
        autorModal.showModal("Promena autora");
    }
}
