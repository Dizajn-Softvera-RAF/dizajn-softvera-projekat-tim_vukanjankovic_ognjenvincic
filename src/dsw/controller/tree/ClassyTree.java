package dsw.controller.tree;

import dsw.controller.tree.model.ClassyTreeItem;
import dsw.controller.tree.view.ClassyTreeView;
import dsw.repository.composite.NodeGenerator;
import dsw.repository.implementation.ProjectExplorer;

public interface ClassyTree {

    ClassyTreeView generateTree(ProjectExplorer projectExplorer, NodeGenerator nodeGenerator);
    void addChild(ClassyTreeItem parent);
    ClassyTreeItem getSelectedNode();
    void removeChild(ClassyTreeItem parent);
    void updateNodeName(ClassyTreeItem node, String name);

    boolean projectExists(ClassyTreeItem node);
    boolean diagramExists(ClassyTreeItem node);
    void deselect();


}
