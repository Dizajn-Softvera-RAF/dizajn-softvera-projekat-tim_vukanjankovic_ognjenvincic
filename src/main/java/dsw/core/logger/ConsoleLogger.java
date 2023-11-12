package dsw.core.logger;

import dsw.observer.ISubscriber;
import dsw.core.ApplicationFramework;

public class ConsoleLogger implements Logger, ISubscriber {
    @Override
    public void log(Message msg) {
        if (ApplicationFramework.getInstance().getMessageGenerator().getType() == MessageGeneratorImpl.FILE || ApplicationFramework.getInstance().getMessageGenerator().getType() == MessageGeneratorImpl.NONE) return;
        System.out.println(msg.getColor() + String.format("[%s] [%s] [%s]", msg.getType(), msg.getTime(), msg.getText()));
    }

    @Override
    public void update(Object notification) {
        Message msg = (Message) notification;
        log(msg);
    }
}
