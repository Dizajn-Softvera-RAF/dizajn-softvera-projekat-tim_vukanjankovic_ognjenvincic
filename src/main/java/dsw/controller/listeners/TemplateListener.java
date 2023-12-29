package dsw.controller.listeners;

import dsw.core.Config;
import dsw.core.serializer.Serializer;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TemplateListener extends MouseAdapter {

    String templateName;
    JDialog dialog;

    public TemplateListener(String templateName, JDialog dialog) {
        this.templateName = templateName;
        this.dialog = dialog;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        if (e.getClickCount() == 1) {
            Serializer.importProject(Config.TEMPLATE_FOLDER + templateName + ".classyCraft");
            dialog.dispose();
        }
    }
}