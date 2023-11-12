package dsw.view.modals;

import dsw.core.Utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class InfoModal extends JDialog implements Modal{
    JPanel content;

    public InfoModal(JFrame parent) {
        super(parent, true);

        JPanel gui = new JPanel(new BorderLayout(3, 3));
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        content = new JPanel(new BorderLayout());
        gui.add(content, BorderLayout.CENTER);
        JPanel info = new JPanel(new GridLayout(2, 2, 0, 20));
        gui.add(info, BorderLayout.SOUTH);


        JLabel ime1 = new JLabel("Vukan Jankovic, 64/21RN");
        ime1.setIcon(Utils.loadIcon("/images/vukan.jpg", 90, 120));

        JLabel ime2 = new JLabel("Ognjen Vincic, 22/20RN");
        ime2.setIcon(Utils.loadIcon("/images/ognjen.jpg", 90, 120));
        info.add(ime1);
        info.add(ime2);

        setContentPane(gui);

    }
    @Override
    public void showModal(String title) {
        setTitle(title);
        pack();
        setLocationRelativeTo(getParent());
        setVisible(true);
    }
}
