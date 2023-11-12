package dsw.view.modals;

import dsw.controller.tree.model.ClassyTreeItem;
import dsw.controller.listeners.CloseModalAction;
import dsw.controller.listeners.PromenaNazivaAction;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class NazivModal extends JDialog implements Modal {

    JDialog jDialog;
    JPanel content;
    JTextField tf;
    JButton jb1;
    JButton jb2;

    public NazivModal(JFrame parent, ClassyTreeItem mapTreeItem) {
        super(parent, true);

        JPanel gui = new JPanel();
        gui.setLayout(new BoxLayout(gui, BoxLayout.Y_AXIS));
        gui.setBorder(new EmptyBorder(new Insets(10,10,10,10)));
        content = new JPanel(new BorderLayout());
        gui.add(content, BorderLayout.CENTER);

        jDialog = this;
        tf = new JTextField(mapTreeItem.getClassyNode().getName());
        jb1 = new JButton("OK");
        jb2 = new JButton("Cancel");
        tf.setSize(new Dimension(Integer.MAX_VALUE, 10));
        jb2.addActionListener(new CloseModalAction(this));
        jb1.addActionListener(new PromenaNazivaAction(tf, this));

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

    public void showModal(String title) {
        setTitle(title);
        pack();
        setLocationRelativeTo(getParent());
        setVisible(true);
    }
}

