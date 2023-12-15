package dsw.repository.implementation;

import dsw.model.DiagramModel;
import dsw.model.elements.*;
import dsw.model.elements.Enum;
import dsw.observer.IPublisher;
import dsw.observer.ISubscriber;
import dsw.repository.composite.ClassyNode;
import dsw.repository.composite.ClassyNodeComposite;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Diagram extends ClassyNodeComposite implements IPublisher {

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

    @Override
    public void addChild(ClassyNode node) {
        if(node != null && node instanceof Klasa)
        {
            Klasa klasa = (Klasa) node;

            if(!this.getChildren().contains(klasa))
                this.getChildren().add(klasa);

        }
        else if(node != null && node instanceof Interfejs)
        {
            Interfejs interfejs = (Interfejs) node;

            if(!this.getChildren().contains(interfejs)) {
                this.getChildren().add(interfejs);
            }
        }else if(node != null && node instanceof Enum)
        {
            Enum anEnum = (Enum) node;

            if(!this.getChildren().contains(anEnum)) {
                this.getChildren().add(anEnum);
            }
        }else if(node != null && node instanceof Agregacija)
        {
            Agregacija agregacija = (Agregacija) node;

            if(!this.getChildren().contains(agregacija)) {
                this.getChildren().add(agregacija);
            }
        }else if(node != null && node instanceof Kompozicija)
        {
            Kompozicija kompozicija = (Kompozicija) node;

            if(!this.getChildren().contains(kompozicija)) {
                this.getChildren().add(kompozicija);
            }
        }else if(node != null && node instanceof Zavisnost)
        {
            Zavisnost zavisnost = (Zavisnost) node;

            if(!this.getChildren().contains(zavisnost)) {
                this.getChildren().add(zavisnost);
            }
        }else if(node != null && node instanceof Generalizacija)
        {
            Generalizacija generalizacija = (Generalizacija) node;

            if(!this.getChildren().contains(generalizacija)) {
                this.getChildren().add(generalizacija);
            }
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

    public DiagramModel getModel() {
        return model;
    }
    public void setModel(DiagramModel model) {
        this.model = model;
    }
}
