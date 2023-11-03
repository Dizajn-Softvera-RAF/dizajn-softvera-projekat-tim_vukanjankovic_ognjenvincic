package dsw.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActionManager {

    private InfoAction infoAction;
    private ExitAction exitAction;
    private DeleteAction deleteAction;
    private NewProjectAction newProjectAction;
    private NewDiagramAction newDiagramAction;
    private RenameAction renameAction;


    public ActionManager(){initialiseActions();}

    private void initialiseActions(){
        infoAction = new InfoAction();
        exitAction = new ExitAction();
        deleteAction = new DeleteAction();
        newProjectAction = new NewProjectAction();
        newDiagramAction = new NewDiagramAction();
        renameAction = new RenameAction();
    }

}
