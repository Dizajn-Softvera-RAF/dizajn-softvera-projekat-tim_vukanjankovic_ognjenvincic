package dsw.repository.implementation;

import dsw.observer.IPublisher;
import dsw.observer.ISubscriber;
import dsw.repository.composite.ClassyNode;
import dsw.repository.composite.ClassyNodeComposite;
import dsw.view.MainFrame;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Project extends ClassyNodeComposite implements IPublisher {

    List<ISubscriber> subs;
    String autor;
    String savePath;


    public Project(String name, ClassyNode parent){
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
    public void addChild(ClassyNode child) {

    }

    @Override
    public void addChild(ClassyNodeComposite child) {

    }

    @Override
    public void deleteChild(ClassyNodeComposite child) {

    }

    @Override
    public void deleteChild(ClassyNode child) {
        if(child instanceof Diagram){
            Diagram diagram = (Diagram) child;
            if(this.getChildren().contains(diagram)){
                this.getChildren().remove(diagram);
                notifySubscribers(this);
            }
        }
    }
}
