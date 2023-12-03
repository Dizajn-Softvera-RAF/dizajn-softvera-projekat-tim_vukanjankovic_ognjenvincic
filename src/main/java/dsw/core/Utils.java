package dsw.core;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Utils {
    public static ImageIcon loadIcon(String fileName, int width, int height){

        URL imageURL = Utils.class.getResource(fileName);
        ImageIcon icon = null;

        if (imageURL != null) {
            icon = new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        }
        else {
            System.err.println("Resource not found: " + fileName);
        }
        return icon;
    }
}
