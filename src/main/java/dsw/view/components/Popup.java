package dsw.view.components;

import dsw.core.logger.Message;

import javax.swing.*;

public class Popup extends JOptionPane {
    public Popup(Message msg) {
        if (msg.getType().equals("INFO")) {
            showMessageDialog(null, msg.getText(), "Info", INFORMATION_MESSAGE);
        } else if (msg.getType().equals("WARN")) {
            showMessageDialog(null, msg.getText(), "Warning", WARNING_MESSAGE);
        } else if (msg.getType().equals("ERROR")) {
            showMessageDialog(null, msg.getText(), "Error", ERROR_MESSAGE);
        }
    }


}
