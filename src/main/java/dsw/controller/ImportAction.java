package dsw.controller;

import dsw.core.serializer.Serializer;
import dsw.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ImportAction extends AbstractClassyAction{

    public ImportAction() {
        putValue(SMALL_ICON, loadIcon("/images/import.png"));
        putValue(NAME, "Import");
        putValue(SHORT_DESCRIPTION, "Import");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jfc.setDialogTitle("Import project");
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
        jfc.showOpenDialog(MainFrame.getInstance());
        if (jfc.getSelectedFile() != null) {
            Serializer.importProject(jfc.getSelectedFile().getAbsolutePath());
        }

    }
}
