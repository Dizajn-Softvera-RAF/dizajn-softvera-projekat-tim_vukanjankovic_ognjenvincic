package dsw.controller.tree.model;

import dsw.repository.composite.ClassyNode;
import lombok.Getter;
import lombok.Setter;

import javax.swing.tree.DefaultMutableTreeNode;

@Getter
@Setter
public class ClassyTreeItem extends DefaultMutableTreeNode {

    private ClassyNode classyNode;

    public ClassyTreeItem(ClassyNode nodeModel){
        this.classyNode = nodeModel;
        classyNode.setClassyTreeItem(this);

    }

    @Override
    public String toString() {
        return classyNode.getName();
    }

    public void setName(String name){this.classyNode.setName(name);}

}
