package dsw.view.components;

import dsw.controller.tree.ClassyTreeImpl;
import dsw.controller.tree.controller.ClassyTreeMouseListener;
import dsw.controller.tree.model.ClassyTreeItem;
import dsw.repository.composite.ClassyNode;
import dsw.repository.composite.ClassyNodeComposite;
import dsw.repository.implementation.Diagram;
import dsw.view.DiagramView;
import dsw.view.MainFrame;

import javax.swing.*;
import java.util.List;

public class TabbedPane extends JTabbedPane {

    public TabbedPane(){
    }

    public void selectDiagram(ClassyNode diagram){
        for(int i=0; i<getTabCount(); i++){
            if(getComponentAt(i) instanceof DiagramView){
                DiagramView view = (DiagramView) getComponentAt(i);
                if(view.getDiagram().equals(diagram)){
                    setSelectedIndex(i);
                    return;
                }
            }
        }
    }
        public void setTabs(List<ClassyNode> nodes) {
            removeAll();
            for (ClassyNode node : nodes) {
                DiagramView view = new DiagramView((Diagram) node);
                addTab(node.getName(), view);
            }

            ClassyTreeItem selected = MainFrame.getInstance().getClassyTree().getSelectedNode();
            if (selected != null && selected.getClassyNode() instanceof Diagram) {
                for (int i = 0; i < getTabCount(); i++) {
                    if (getTitleAt(i).equals(selected.getClassyNode().getName())) {
                        setSelectedIndex(i);
                        return;
                    }
                }
            }
        }

}
