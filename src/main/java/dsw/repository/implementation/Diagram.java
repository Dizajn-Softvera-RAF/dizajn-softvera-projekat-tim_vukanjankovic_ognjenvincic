package dsw.repository.implementation;

import dsw.model.DiagramModel;
import dsw.observer.IPublisher;
import dsw.observer.ISubscriber;
import dsw.repository.composite.ClassyNode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Diagram extends ClassyNode implements IPublisher {

    private List<ISubscriber> subs;

    @Override
    public void addSubscriber(ISubscriber sub) {
        subs.add(sub);
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        subs.remove(sub);
    }

    @Override
    public void notifySubscribers(Object notification) {
        for(ISubscriber sub: subs){
            sub.update(notification);
        }
    }

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
