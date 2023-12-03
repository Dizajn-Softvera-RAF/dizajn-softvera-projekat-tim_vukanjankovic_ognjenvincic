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
    private NewPackageAction newPackageAction;
    private AutorAction autorAction;
    private NewConnectionAction connectionAction;
    private NewElementAction elementAction;

    public ActionManager(){initialiseActions();}

    private void initialiseActions(){
        infoAction = new InfoAction();
        exitAction = new ExitAction();
        deleteAction = new DeleteAction();
        newProjectAction = new NewProjectAction();
        newDiagramAction = new NewDiagramAction();
        renameAction = new RenameAction();
        newPackageAction = new NewPackageAction();
        autorAction = new AutorAction();
        connectionAction = new NewConnectionAction();
        elementAction = new NewElementAction();
    }

}
