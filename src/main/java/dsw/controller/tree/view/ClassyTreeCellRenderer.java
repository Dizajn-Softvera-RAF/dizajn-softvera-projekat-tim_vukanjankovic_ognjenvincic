package dsw.controller.tree.view;

import dsw.controller.tree.model.ClassyTreeItem;
import dsw.repository.implementation.Diagram;
import dsw.repository.implementation.Package;
import dsw.repository.implementation.Project;
import dsw.repository.implementation.ProjectExplorer;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.net.URL;

public class ClassyTreeCellRenderer extends DefaultTreeCellRenderer {

    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus ){

        super.getTreeCellRendererComponent(tree, value, sel,expanded, leaf, row, hasFocus);
        URL imageURL = null;

        if (((ClassyTreeItem)value).getClassyNode() instanceof ProjectExplorer) {
            imageURL = getClass().getResource("/images/projectExplorer.png");
        }
        else if (((ClassyTreeItem)value).getClassyNode() instanceof Project) {

            imageURL = getClass().getResource("/images/tproject.png");
        }
        else if (((ClassyTreeItem)value).getClassyNode() instanceof Diagram) {
            imageURL = getClass().getResource("/images/diagram.png");
        }

        else if (((ClassyTreeItem)value).getClassyNode() instanceof Package) {
            imageURL = getClass().getResource("/images/package.png");
        }

        Icon icon = null;
        if (imageURL != null)
            icon = new ImageIcon(imageURL);
        setIcon(icon);

        return this;
    }

}
