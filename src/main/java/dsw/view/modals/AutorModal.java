package dsw.view.modals;

import dsw.controller.tree.model.ClassyTreeItem;
import dsw.controller.listeners.CloseModalAction;
import dsw.controller.listeners.PromenaAutoraAction;
import dsw.repository.implementation.Diagram;
import dsw.repository.implementation.Package;
import dsw.repository.implementation.Project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AutorModal extends JDialog implements Modal {

    JDialog jDialog;
    JPanel content;
    JTextField tf;
    JButton jb1;
    JButton jb2;


    public AutorModal(JFrame parent, ClassyTreeItem classyTreeItem){
        super(parent, true);

        JPanel gui = new JPanel();
        gui.setLayout(new BoxLayout(gui, BoxLayout.Y_AXIS));
        gui.setBorder(new EmptyBorder(new Insets(10,10,10,10)));
        content = new JPanel(new BorderLayout());
        gui.add(content, BorderLayout.CENTER);

        jDialog = this;
        String current = "";
        if(classyTreeItem.getClassyNode() instanceof Project) current = ((Project) classyTreeItem.getClassyNode()).getAutor();
        if(classyTreeItem.getClassyNode() instanceof Package) current = ((Project) classyTreeItem.getClassyNode().getParent()).getAutor();
        if(classyTreeItem.getClassyNode() instanceof Diagram) current = ((Project) classyTreeItem.getClassyNode().getParent()).getAutor();
        tf = new JTextField(current);
        jb1 = new JButton("OK");
        jb2 = new JButton("Cancel");
        tf.setSize(new Dimension(Integer.MAX_VALUE, 10));
        jb2.addActionListener(new CloseModalAction(this));
        jb1.addActionListener(new PromenaAutoraAction(tf, classyTreeItem, this));

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
