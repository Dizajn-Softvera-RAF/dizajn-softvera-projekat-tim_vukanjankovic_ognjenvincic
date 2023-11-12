package dsw.repository.implementation;

import dsw.model.DiagramModel;
import dsw.repository.composite.ClassyNode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Diagram extends ClassyNode {

    private DiagramModel model;
    public Diagram(String name, ClassyNode parent) {
        super(name, parent);
        setClassyTreeItem(parent.getClassyTreeItem());
        model = new DiagramModel();
    }

    public DiagramModel getModel() {
        return model;
    }
    public void setModel(DiagramModel model) {
        this.model = model;
    }
}
