package dsw.controller;

import dsw.core.ApplicationFramework;
import dsw.core.Utils;
import dsw.core.logger.MessageType;
import dsw.view.DiagramView;
import dsw.view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ZoomOutAction extends AbstractClassyAction{

    public ZoomOutAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, Utils.loadIcon("/images/zoomOut.png", 32, 32));
        putValue(NAME, "Zoom out");
        putValue(SHORT_DESCRIPTION, "Zoom out");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DiagramView dv = (DiagramView) MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent();
        if (dv == null) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessageType.NODE_NOT_SELECTED);
            return;
        }

        if(dv.getDiagram().getModel().getZoom() <= 1)return;
        double prevZoom = dv.getDiagram().getModel().getZoom();
        dv.getDiagram().getModel().setZoom(dv.getDiagram().getModel().getZoom()-0.1);

        Point translation = Utils.getTranslation(dv, prevZoom);
        dv.getDiagram().getModel().setTransformX(translation.x);
        dv.getDiagram().getModel().setTransformY(translation.y);
        dv.getDiagram().getModel().notifySubscribers(null);


    }

}
