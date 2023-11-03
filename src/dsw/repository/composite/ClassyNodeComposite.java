package dsw.repository.composite;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class ClassyNodeComposite extends ClassyNode{

    List<ClassyNode> children;

    protected ClassyNodeComposite(String name, ClassyNode parent){
        super(name, parent);
        this.children = new ArrayList<>();
    }

    public abstract void addChild(ClassyNode child);
    public abstract void addChild(ClassyNodeComposite child);
    public abstract void deleteChild(ClassyNodeComposite child);
    public abstract void deleteChild(ClassyNode child);


}
