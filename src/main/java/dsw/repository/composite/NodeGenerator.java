package dsw.repository.composite;

import dsw.controller.tree.model.ClassyTreeItem;
import dsw.observer.IPublisher;
import dsw.observer.ISubscriber;
import dsw.view.MainFrame;
import dsw.repository.implementation.*;
import dsw.repository.implementation.Package;

import java.util.ArrayList;
import java.util.List;

public class NodeGenerator implements NodeFactory, IPublisher {

    private int type;
    private List<ISubscriber> subs;


    public NodeGenerator(){
        subs = new ArrayList<>();
        subs.add(MainFrame.getInstance().getClassyTree());
    }

    @Override
    public void addSubscriber(ISubscriber sub) {
        if(!subs.contains(sub))
            subs.add(sub);
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        if(subs.contains(sub))
            subs.remove(sub);
    }

    @Override
    public void notifySubscribers(Object notification) {
        for(ISubscriber sub: subs)
            sub.update(notification);
    }
    @Override
    public ClassyNode generateNode(NodeType type, ClassyTreeItem parent) {

        ClassyNode node = null;

        if(type == NodeType.ProjectExplorer) {
            node = new ProjectExplorer("ProjectExplorer");
            node.setClassyTreeItem(parent);
        }

        if(type == NodeType.Project){
            node = new Project("Project" + (parent.getChildCount()+1), parent.getClassyNode());
            node.setClassyTreeItem(parent);
        }
        if(type == NodeType.Diagram) {
            node = new Diagram("Diagram" + (parent.getChildCount()+1), parent.getClassyNode());
            node.setClassyTreeItem(parent);
        }
        if(type == NodeType.Package) {
            node = new Package("Package" + (parent.getChildCount()+1), parent.getClassyNode());
            node.setClassyTreeItem(parent);
        }
        if (node != null){
            notifySubscribers(node);
        }
        return node;
    }

}
