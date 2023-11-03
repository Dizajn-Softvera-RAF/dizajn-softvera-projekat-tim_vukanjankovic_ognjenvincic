package dsw.core.logger;

import dsw.observer.IPublisher;
import dsw.observer.ISubscriber;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MessageGeneratorImpl implements IPublisher, MessageGenerator {

    public static final int NONE = 0;
    public static final int CONSOLE = 1;
    public static final int FILE = 2;
    public static final int ALL = 3;

    private int type;
    private List<ISubscriber> subs;

    public MessageGeneratorImpl(int type) {
        this.type = type;
        subs = new ArrayList<>();
    }

    @Override
    public void generateMessage(MessageType type) {
        Message msg = new Message(Message.INFO, "");
        if(type == MessageType.CANT_DELETE) msg = new Message(Message.ERROR, "THIS NODE CANNOT BE DELETED");
        if(type == MessageType.EMPTY_FIELD) msg = new Message(Message.WARN, "THIS FIELD CANNOT BE EMPTY");

        if(type == MessageType.NODE_NOT_SELECTED) msg = new Message(Message.WARN, "NODE NOT SELECTED");

        if(type == MessageType.PROJECT_DOESNT_EXIST) msg = new Message(Message.ERROR, "THIS PROJECT NO LONGER EXISTS");


        notifySubscribers(msg);

    }

    @Override
    public void addSubscriber(ISubscriber sub) {
        if (!subs.contains(sub)) subs.add(sub);
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        if (subs.contains(sub)) subs.remove(sub);
    }

    @Override
    public void notifySubscribers(Object notification) {
        for (ISubscriber sub : subs) {
            sub.update(notification);
        }
    }
}
