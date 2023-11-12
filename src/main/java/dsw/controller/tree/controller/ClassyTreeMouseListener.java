package dsw.controller.tree.controller;

import dsw.controller.popups.PopupFactory;
import dsw.controller.popups.PopupType;
import dsw.controller.tree.model.ClassyTreeItem;
import dsw.repository.implementation.Diagram;
import dsw.repository.implementation.Project;
import dsw.repository.implementation.ProjectExplorer;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClassyTreeMouseListener extends MouseAdapter {


    private PopupFactory popupFactory;

    public ClassyTreeMouseListener() {
        popupFactory = new PopupFactory();
    }

    private void show(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        JTree tree = (JTree) e.getSource();
        TreePath path = tree.getPathForLocation(x, y);
        if (path == null)
            return;

        DefaultMutableTreeNode rightClickedNode = (DefaultMutableTreeNode) path.getLastPathComponent();

        TreePath[] selectionPaths = tree.getSelectionPaths();

        boolean isSelected = false;
        if (selectionPaths != null) {
            for (TreePath selectionPath : selectionPaths) {
                if (selectionPath.equals(path)) {
                    isSelected = true;
                }
            }
        }
        if (!isSelected) {
            tree.setSelectionPath(path);
        }

        if (((ClassyTreeItem) rightClickedNode).getClassyNode() instanceof ProjectExplorer) {
            JPopupMenu popup = (JPopupMenu) popupFactory.getPopup(PopupType.PROJECT_EXPLORER, (ClassyTreeItem) rightClickedNode);
            popup.show(tree, x, y);
        }
        if (((ClassyTreeItem) rightClickedNode).getClassyNode() instanceof Project) {
            JPopupMenu popup = (JPopupMenu) popupFactory.getPopup(PopupType.PROJECT, (ClassyTreeItem) rightClickedNode);
            popup.show(tree, x, y);
        }
        if (((ClassyTreeItem) rightClickedNode).getClassyNode() instanceof Diagram) {
            JPopupMenu popup = (JPopupMenu) popupFactory.getPopup(PopupType.DIAGRAM, (ClassyTreeItem) rightClickedNode);
            popup.show(tree, x, y);
        }

    }
    public void mousePressed(MouseEvent e) {
        if (e.isPopupTrigger()) {
            show(e);
        }
    }
    public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger())
            show(e);
    }


}
