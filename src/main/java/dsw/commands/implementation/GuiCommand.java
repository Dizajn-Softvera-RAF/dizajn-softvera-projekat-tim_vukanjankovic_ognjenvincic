package dsw.commands.implementation;

import dsw.commands.AbstractCommand;
import dsw.model.DiagramModel;
import dsw.repository.composite.ClassyNodeComposite;
import dsw.repository.implementation.Diagram;
import dsw.view.MainFrame;

public class GuiCommand extends AbstractCommand {

    private DiagramModel oldModel;
    private DiagramModel newModel;
    private Diagram diagram;

    public GuiCommand(DiagramModel oldModel, DiagramModel newModel, Diagram diagram) {
        this.newModel = newModel;
        this.oldModel = oldModel;
        this.diagram = diagram;
    }

    @Override
    public void DoCommand() {
        if(oldModel == null ||  newModel==null) return;
        newModel.setTransformX(diagram.getModel().getTransformX());
        newModel.setTransformY(diagram.getModel().getTransformY());
        newModel.setZoom(diagram.getModel().getZoom());
        diagram.setModel(newModel.getClone());
        diagram.getModel().notifySubscribers(null);
        MainFrame.getInstance().getProjectView().getTabbedPane().setTabs(((ClassyNodeComposite)diagram.getParent()).getChildren());
        MainFrame.getInstance().getProjectView().getTabbedPane().selectDiagram(diagram);
    }

    @Override
    public void UndoCommand() {
        if(oldModel == null ||  newModel==null) return;
        oldModel.setTransformX(diagram.getModel().getTransformX());
        oldModel.setTransformY(diagram.getModel().getTransformY());
        oldModel.setZoom(diagram.getModel().getZoom());
        diagram.setModel(oldModel.getClone());
        diagram.getModel().notifySubscribers(null);

        MainFrame.getInstance().getProjectView().getTabbedPane().setTabs(((ClassyNodeComposite)diagram.getParent()).getChildren());
        MainFrame.getInstance().getProjectView().getTabbedPane().selectDiagram(diagram);
    }
}
