package dsw.controller;

public class ActionManager {

    private InfoAction infoAction;
    private ExitAction exitAction;

    public ActionManager(){initialiseActions();}

    private void initialiseActions(){
        infoAction = new InfoAction();
        exitAction = new ExitAction();
    }


    public InfoAction getInfoAction() {
        return infoAction;
    }

    public void setInfoAction(InfoAction infoAction) {
        this.infoAction = infoAction;
    }

    public ExitAction getExitAction() {
        return exitAction;
    }

    public void setExitAction(ExitAction exitAction) {
        this.exitAction = exitAction;
    }
}
