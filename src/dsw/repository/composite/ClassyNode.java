package dsw.repository.composite;

import dsw.controller.tree.model.ClassyTreeItem;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public abstract class ClassyNode {

    private String name;
    @ToString.Exclude
    private ClassyNode parent;
    private ClassyTreeItem classyTreeItem;

    protected ClassyNode(String name, ClassyNode parent){
        this.name = name;
        this.parent = parent;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ClassyNode){
            ClassyNode otherObj = (ClassyNode) obj;
            return this.getName().equals(otherObj.getName());
        }
        return false;
    }

}
