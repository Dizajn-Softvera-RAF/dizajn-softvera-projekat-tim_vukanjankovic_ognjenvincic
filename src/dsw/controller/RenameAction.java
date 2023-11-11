package dsw.controller;

import dsw.controller.popups.PopupAction;
import dsw.controller.tree.model.ClassyTreeItem;
import dsw.core.ApplicationFramework;
import dsw.core.logger.MessageType;
import dsw.repository.implementation.Diagram;
import dsw.repository.implementation.Package;
import dsw.repository.implementation.Project;
import dsw.repository.implementation.ProjectExplorer;
import dsw.view.MainFrame;
import dsw.view.modals.Modal;
import dsw.view.modals.NazivModal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class RenameAction  extends AbstractClassyAction implements PopupAction {

    public RenameAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/edit_project.png"));
        putValue(NAME, "Rename");
        putValue(SHORT_DESCRIPTION, "Rename");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ClassyTreeItem selected = MainFrame.getInstance().getClassyTree().getSelectedNode();
        if(selected == null || selected.getClassyNode() instanceof ProjectExplorer) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessageType.NODE_NOT_SELECTED);
            return;
        }
        if (selected.getClassyNode() instanceof Diagram) {
            if (!MainFrame.getInstance().getClassyTree().projectExists((ClassyTreeItem) selected.getParent()) || !MainFrame.getInstance().getClassyTree().diagramExists(selected))
                return;
        }
        if (selected.getClassyNode() instanceof Project) {
            if (!MainFrame.getInstance().getClassyTree().projectExists(selected)) {
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessageType.PROJECT_DOESNT_EXIST);
                return;
            }
        }
        if (selected.getClassyNode() instanceof Package) {
            if (!MainFrame.getInstance().getClassyTree().projectExists(selected)) {
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessageType.PROJECT_DOESNT_EXIST);
                return;
            }
        }
        Modal modal = new NazivModal(MainFrame.getInstance(), selected);
        modal.showModal("Promena naziva");
    }

    @Override
    public void popupAction(Object component) {
        ClassyTreeItem selected = (ClassyTreeItem) component;
        Modal modal = new NazivModal(MainFrame.getInstance(), selected);
        modal.showModal("Promena naziva");
    }

}
