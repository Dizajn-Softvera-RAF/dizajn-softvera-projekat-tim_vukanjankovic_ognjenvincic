package dsw.commands.implementation;

import dsw.commands.AbstractCommand;
import dsw.controller.tree.model.ClassyTreeItem;
import dsw.repository.composite.ClassyNodeComposite;

public class DiagramCommand extends AbstractCommand {
    private ClassyTreeItem child;
    private ClassyTreeItem parent;

    public DiagramCommand(ClassyTreeItem parent, ClassyTreeItem child) {
        this.parent = parent;
        this.child = child;
    }

    @Override
    public void DoCommand() {
        if(child == null ||  parent==null) return;
        parent.add(child);
        ((ClassyNodeComposite) parent.getClassyNode()).addChild(child.getClassyNode());
    }

    @Override
    public void UndoCommand() {
        if(child == null ||  parent==null) return;
        child.removeFromParent();
        ((ClassyNodeComposite)(parent.getClassyNode())).deleteChild(child.getClassyNode());
    }
}
