package dsw.controller;

import dsw.view.MainFrame;
import dsw.view.modals.Modal;
import dsw.view.modals.TemplateModal;

import java.awt.event.ActionEvent;

public class TemplateGalleryAction extends AbstractClassyAction{

    public TemplateGalleryAction() {
        putValue(SMALL_ICON, loadIcon("/images/template.png"));
        putValue(NAME, "Template Gallery");
        putValue(SHORT_DESCRIPTION, "Template Gallery");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Modal modal = new TemplateModal(MainFrame.getInstance());
        modal.showModal("Template Gallery");
    }

}
