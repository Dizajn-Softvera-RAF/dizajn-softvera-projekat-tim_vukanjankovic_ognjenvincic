package dsw.core;

import dsw.commands.CommandManager;

public interface Gui {
    void start();

    CommandManager getCommandManager();

    void enableUndo(boolean enable);
    void enableRedo(boolean enable);
}
