package dsw.controller.tree.view;

import dsw.controller.tree.controller.ClassyTreeCellEditor;
import dsw.controller.tree.controller.ClassyTreeMouseListener;
import dsw.controller.tree.controller.ClassyTreeSelectionListener;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

public class ClassyTreeView extends JTree {

    public ClassyTreeView(DefaultTreeModel defaultTreeModel){

        setModel(defaultTreeModel);
        ClassyTreeCellRenderer classyTreeCellRenderer = new ClassyTreeCellRenderer();
        addTreeSelectionListener(new ClassyTreeSelectionListener());
        addMouseListener(new ClassyTreeMouseListener());
        setCellEditor(new ClassyTreeCellEditor(this, classyTreeCellRenderer));
        setCellRenderer(classyTreeCellRenderer);
        setEditable(true);

    }


}
