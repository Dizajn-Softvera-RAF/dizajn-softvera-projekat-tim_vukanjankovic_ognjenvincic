package dsw.repository.implementation;

import dsw.observer.IPublisher;
import dsw.observer.ISubscriber;
import dsw.repository.composite.ClassyNode;
import dsw.repository.composite.ClassyNodeComposite;
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
    public void addChild(ClassyNode node) {
        if(node != null && node instanceof Package)
        {
            Package pack = (Package) node;

            if(!this.getChildren().contains(pack))
                this.getChildren().add(pack);

        }
        else if(node != null && node instanceof Diagram)
        {
            Diagram diagram = (Diagram) node;

            if(!this.getChildren().contains(diagram)) {
                this.getChildren().add(diagram);
            }
        }
    }

    @Override
    public void addChild(ClassyNodeComposite node) {
        if(node != null && node instanceof Package)
        {
            Package pack = (Package) node;

            if(!this.getChildren().contains(pack))
                this.getChildren().add(pack);

        }
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

        if(child instanceof Package){
            Package pcg = (Package) child;
            if(this.getChildren().contains(pcg)){
                this.getChildren().remove(pcg);
                notifySubscribers(this);
            }
        }
    }
}
