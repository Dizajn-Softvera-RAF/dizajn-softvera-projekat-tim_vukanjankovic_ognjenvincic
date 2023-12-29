package dsw.commands;

import dsw.core.ApplicationFramework;
import dsw.view.MainFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CommandManager {

    private List<AbstractCommand> commands = new ArrayList<>();
    private int currentCommand = 0;

    public void addCommand(AbstractCommand command){
        while(currentCommand < commands.size())
            commands.remove(currentCommand);
        commands.add(command);
        doCommand();
    }

    public void doCommand(){

        if(currentCommand < commands.size()){
            commands.get(currentCommand++).DoCommand();
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getClassyTree().getTreeView());
            ApplicationFramework.getInstance().getGui().enableUndo(true);
        }
        if(currentCommand==commands.size()){
            ApplicationFramework.getInstance().getGui().enableRedo(false);
        }
    }

    public void undoCommand(){
        if(currentCommand > 0){
            commands.get(--currentCommand).UndoCommand();
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getClassyTree().getTreeView());
            ApplicationFramework.getInstance().getGui().enableRedo(true);
        }
        if(currentCommand==0){
            ApplicationFramework.getInstance().getGui().enableUndo(false);
        }
    }

    public int getCommands(){
        return commands.size();
    }

}
