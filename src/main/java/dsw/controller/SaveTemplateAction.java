package dsw.controller;

import dsw.core.serializer.Serializer;
import dsw.repository.implementation.Project;
import dsw.view.MainFrame;

import java.awt.event.ActionEvent;

public class SaveTemplateAction extends AbstractClassyAction{

    public SaveTemplateAction() {
        putValue(SMALL_ICON, loadIcon("/images/add_template.png"));
        putValue(NAME, "Save Template");
        putValue(SHORT_DESCRIPTION, "Save Template");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Project p = MainFrame.getInstance().getProjectView().getProject();

        if (p == null) return;

        Serializer.saveFile(p, true);
    }
}
