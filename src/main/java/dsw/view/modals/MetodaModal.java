package dsw.view.modals;

import dsw.controller.listeners.AtributActionListener;
import dsw.controller.listeners.CloseModalAction;
import dsw.controller.listeners.MetodaActionListener;
import dsw.model.elements.Interclass;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MetodaModal extends JDialog implements Modal {

    JDialog jDialog;
    JPanel content;
    JTextField tf;
    JButton jb1;
    JButton jb2;

    public MetodaModal(JFrame parent, Interclass interclass){
        super(parent, true);
        JPanel gui = new JPanel();
        gui.setLayout(new BoxLayout(gui, BoxLayout.Y_AXIS));
        gui.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        content = new JPanel(new BorderLayout());
        gui.add(content, BorderLayout.CENTER);

        jDialog = this;
        String current = "";
        tf = new JTextField(current);
        jb1 = new JButton("OK");
        jb2 = new JButton("Cancel");
        tf.setSize(new Dimension(Integer.MAX_VALUE, 10));
        jb2.addActionListener(new CloseModalAction(this));
        jb1.addActionListener(new MetodaActionListener(tf, interclass, this));

        JPanel jp1 = new JPanel();
        jp1.setLayout(new BoxLayout(jp1, BoxLayout.Y_AXIS));
        jp1.add(tf);

        JPanel jp2 = new JPanel(new FlowLayout());
        jp2.add(jb1);
        jp2.add(jb2);

        gui.add(jp1);
        gui.add(jp2);

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
