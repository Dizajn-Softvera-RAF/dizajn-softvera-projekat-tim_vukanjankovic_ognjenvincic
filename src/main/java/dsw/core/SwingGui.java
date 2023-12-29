package dsw.core;

import dsw.commands.CommandManager;
import dsw.core.logger.Message;
import dsw.observer.ISubscriber;
import dsw.view.MainFrame;

public class SwingGui implements Gui, ISubscriber {

    private CommandManager commandManager;
    @Override
    public void start() {
        MainFrame.getInstance().setVisible(true);
        commandManager = new CommandManager();
        enableUndo(false);
        enableRedo(false);
    }


    @Override
    public void enableUndo(boolean enable) {
        MainFrame.getInstance().getPallete().setUndoEnabled(enable);
    }

    @Override
    public void enableRedo(boolean enable) {
        MainFrame.getInstance().getPallete().setRedoEnabled(enable);
    }

    @Override
    public CommandManager getCommandManager() {
        return commandManager;
    }

    @Override
    public void update(Object notification) {
        Message msg = (Message) notification;

    }
}
