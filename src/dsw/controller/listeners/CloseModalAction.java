package dsw.controller.listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CloseModalAction implements ActionListener {

    JDialog jd;

    public CloseModalAction(JDialog dialog) {
        jd = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        jd.dispose();
    }
}