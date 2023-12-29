package dsw.view.modals;

import dsw.controller.listeners.AtributActionListener;
import dsw.controller.listeners.CloseModalAction;
import dsw.controller.tree.model.ClassyTreeItem;
import dsw.model.elements.DiagramElement;
import dsw.model.elements.Interclass;
import dsw.view.DiagramView;
import dsw.view.painters.InterclassPainter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AtributModal extends JDialog implements Modal {

    JDialog jDialog;
    JPanel content;
    JTextField tf;
    JTextField tf1;
    JTextField tf2;
    JButton jb1;
    JButton jb2;

    public AtributModal(JFrame parent, DiagramElement interclass){
        super(parent, true);
        JPanel gui = new JPanel();
        gui.setLayout(new BoxLayout(gui, BoxLayout.Y_AXIS));
        gui.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        content = new JPanel(new BorderLayout());
        gui.add(content, BorderLayout.CENTER);

        jDialog = this;
        String current = "";
        tf = new JTextField(current);
        tf1 = new JTextField(current);
        tf2 = new JTextField(current);
        jb1 = new JButton("OK");
        jb2 = new JButton("Cancel");
        tf.setSize(new Dimension(Integer.MAX_VALUE, 10));
        tf1.setSize(new Dimension(Integer.MAX_VALUE, 10));
        tf2.setSize(new Dimension(Integer.MAX_VALUE, 10));
        jb2.addActionListener(new CloseModalAction(this));
        jb1.addActionListener(new AtributActionListener(tf, tf1, tf2, interclass, this));

        JPanel jp1 = new JPanel();
        jp1.setLayout(new BoxLayout(jp1, BoxLayout.Y_AXIS));
        jp1.add(tf);
        jp1.add(tf1);
        jp1.add(tf2);

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
