package dsw.controller;

import dsw.commands.AbstractCommand;
import dsw.commands.implementation.GuiCommand;
import dsw.core.ApplicationFramework;
import dsw.core.Utils;
import dsw.core.logger.MessageType;
import dsw.model.DiagramModel;
import dsw.model.elements.ConnectionElement;
import dsw.repository.implementation.Diagram;
import dsw.view.DiagramView;
import dsw.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DeletePainterAction extends AbstractClassyAction{

    public DeletePainterAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, Utils.loadIcon("/images/delete.png", 32, 32));
        putValue(NAME, "Delete");
        putValue(SHORT_DESCRIPTION, "Delete");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DiagramView dv = (DiagramView) MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent();
        if (dv == null) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessageType.NODE_NOT_SELECTED);
            return;
        }
        DiagramModel oldModel = dv.getDiagram().getModel().getClone();


        for(int i =0; i<dv.getDiagram().getModel().getElementCount();i++){
            if(dv.getDiagram().getModel().getSelectedElements().contains(dv.getDiagram().getModel().getDiagramElements().get(i))){
                dv.getDiagram().getModel().deleteElement(dv.getDiagram().getModel().getDiagramElements().get(i));
                i--;
            }
        }
        for (int i = 0; i < dv.getDiagram().getModel().getVeze().size(); i++) {
            if (dv.getDiagram().getModel().getVeze().get(i).getDevice().isSelected()) {
                dv.getDiagram().getModel().deleteConnection(dv.getDiagram().getModel().getVeze().get(i));
                i--;
            }
        }
        AbstractCommand command = new GuiCommand(oldModel, dv.getDiagram().getModel().getClone(), dv.getDiagram());
        ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(command);
        dv.getDiagram().getModel().notifySubscribers(null);
    }
}
