package dsw.repository.implementation;

import dsw.controller.tree.model.ClassyTreeItem;
import dsw.repository.composite.ClassyNode;

public interface NodeFactory {

    ClassyNode generateNode(NodeType type, ClassyTreeItem parent);

}
