package dsw.repository.implementation;

import dsw.observer.IPublisher;
import dsw.observer.ISubscriber;
import dsw.repository.composite.ClassyNodeComposite;
import dsw.repository.composite.ClassyNode;

import java.util.ArrayList;
import java.util.List;

public class Package extends ClassyNodeComposite implements IPublisher {
    private List<ISubscriber> subs;
    public Package(String name, ClassyNode parent) {
        super(name, parent);
        setClassyTreeItem(parent.getClassyTreeItem());
        subs = new ArrayList<>();
    }

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

    @Override
    public void addChild(ClassyNode node) {
        if(node != null && node instanceof Diagram)
        {
            Diagram diagram = (Diagram) node;

            if(!this.getChildren().contains(diagram))
                this.getChildren().add(diagram);
        }

    }

    @Override
    public void addChild(ClassyNodeComposite child) {

    }

    @Override
    public void deleteChild(ClassyNodeComposite child) {

    }

    @Override
    public void deleteChild(ClassyNode child) {

    }
}
