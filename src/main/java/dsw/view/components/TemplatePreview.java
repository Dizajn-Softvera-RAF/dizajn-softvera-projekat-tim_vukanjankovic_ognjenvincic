package dsw.view.components;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class TemplatePreview extends JPanel {

    BufferedImage image;

    public TemplatePreview(BufferedImage image) {
        this.image = image;
        repaint();
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}