package dsw.controller;

import dsw.repository.implementation.Diagram;
import dsw.view.DiagramView;
import dsw.view.MainFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ExportImageAction extends AbstractClassyAction{

    public ExportImageAction() {
        putValue(SMALL_ICON, loadIcon("/images/export_as_image.png"));
        putValue(NAME, "Export image");
        putValue(SHORT_DESCRIPTION, "Export image");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DiagramView dv = (DiagramView) MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent();
        if (dv == null) return;

        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jfc.setDialogTitle("Export image");
        jfc.setAcceptAllFileFilterUsed(false);
        jfc.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(java.io.File f) {
                return f.isDirectory() || f.getAbsolutePath().endsWith(".png");
            }

            @Override
            public String getDescription() {
                return "PNG files (*.png)";
            }
        });

        jfc.showSaveDialog(MainFrame.getInstance());
        if (jfc.getSelectedFile() != null) {
            String path = jfc.getSelectedFile().getAbsolutePath();
            if (!path.endsWith(".png")) {
                path += ".png";
            }
            BufferedImage image = new BufferedImage(dv.getWidth(), dv.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image.createGraphics();
            dv.printAll(g);
            g.dispose();
            try {
                ImageIO.write(image, "png", new File(path));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
