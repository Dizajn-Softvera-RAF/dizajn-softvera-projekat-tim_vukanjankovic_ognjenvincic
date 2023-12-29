package dsw.controller.tree;

import dsw.controller.tree.model.ClassyTreeItem;
import dsw.controller.tree.view.ClassyTreeView;
import dsw.repository.composite.ClassyNode;
import dsw.repository.composite.NodeGenerator;
import dsw.repository.implementation.NodeType;
import dsw.repository.implementation.ProjectExplorer;

public interface ClassyTree {

    ClassyTreeView generateTree(ProjectExplorer projectExplorer, NodeGenerator nodeGenerator);
    ClassyNode addChild(ClassyTreeItem parent, String name);
    void addChild(ClassyTreeItem parent, NodeType child);
    ClassyTreeItem getSelectedNode();
    void removeChild(ClassyTreeItem parent);
    void updateNodeName(ClassyTreeItem node, String name);

    boolean projectExists(ClassyTreeItem node);
    boolean diagramExists(ClassyTreeItem node);
    boolean packageExists(ClassyTreeItem node);
    void deselect();


}
