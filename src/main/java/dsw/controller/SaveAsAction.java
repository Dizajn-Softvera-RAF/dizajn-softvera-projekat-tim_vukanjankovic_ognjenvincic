package dsw.controller;

import dsw.core.serializer.Serializer;
import dsw.repository.implementation.Project;
import dsw.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class SaveAsAction extends AbstractClassyAction{

    public SaveAsAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/save.png"));
        putValue(NAME, "Save as");
        putValue(SHORT_DESCRIPTION, "Save as");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Project p = MainFrame.getInstance().getProjectView().getProject();

        if (p == null) return;

        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jfc.setDialogTitle("Save project");
        if (p.getSavePath() != null) {
            jfc.setSelectedFile(new java.io.File(p.getSavePath()));
        }
        jfc.setAcceptAllFileFilterUsed(false);
        jfc.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(java.io.File f) {
                return f.isDirectory() || f.getAbsolutePath().endsWith(".classyCraft");
            }

            @Override
            public String getDescription() {
                return "ClassyCraft files (*.classyCraft)";
            }
        });
        jfc.showSaveDialog(MainFrame.getInstance());
        if (jfc.getSelectedFile() != null) {
            p.setSavePath(jfc.getSelectedFile().getAbsolutePath());
        }

        if (p.getSavePath() != null) {
            Serializer.saveFile(p, false);
        }
    }
}