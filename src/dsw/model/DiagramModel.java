package dsw.model;

import dsw.observer.IPublisher;
import dsw.observer.ISubscriber;
import dsw.view.DiagramView;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class DiagramModel implements IPublisher {

    ArrayList<ISubscriber> subscribers;
    private String name;



    @Override
    public void addSubscriber(ISubscriber sub) {
        subscribers.add(sub);
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        subscribers.remove(sub);
    }

    @Override
    public void notifySubscribers(Object notification) {
        for(ISubscriber sub: subscribers){
            DiagramView view = (DiagramView) sub;
            if(view!= null){
                sub.update(notification);
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
