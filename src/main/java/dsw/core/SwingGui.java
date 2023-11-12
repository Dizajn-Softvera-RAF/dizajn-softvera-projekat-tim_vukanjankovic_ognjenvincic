package dsw.core;

import dsw.core.logger.Message;
import dsw.observer.ISubscriber;
import dsw.view.MainFrame;

public class SwingGui implements Gui, ISubscriber {
    @Override
    public void start() {
        MainFrame.getInstance().setVisible(true);
    }

    @Override
    public void update(Object notification) {
        Message msg = (Message) notification;

    }
}
