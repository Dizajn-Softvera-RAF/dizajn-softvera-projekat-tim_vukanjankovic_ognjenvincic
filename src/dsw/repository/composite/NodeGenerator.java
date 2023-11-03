package dsw.repository.composite;

import dsw.controller.tree.model.ClassyTreeItem;
import dsw.observer.IPublisher;
import dsw.observer.ISubscriber;
import dsw.repository.implementation.*;

import java.util.ArrayList;
import java.util.List;

public class NodeGenerator implements NodeFactory, IPublisher {

    private int type;
    private List<ISubscriber> subs;


    public NodeGenerator(){
        subs = new ArrayList<>();
    }

    @Override
    public void addSubscriber(ISubscriber sub) {

    }

    @Override
    public void removeSubscriber(ISubscriber sub) {

    }

    @Override
    public void notifySubscribers(Object notification) {

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
        if (node != null){
            notifySubscribers(node);
        }
        return node;
    }

    public ClassyNode generateNode(NodeType type, ClassyTreeItem parent, String name){
        ClassyNode node = null;
        if(type == NodeType.Project){
            node = new Project(name, parent.getClassyNode());
            node.setClassyTreeItem(parent);
        }
        if(type == NodeType.Diagram) {
            node = new Diagram(name, parent.getClassyNode());
            node.setClassyTreeItem(parent);
        }
        if (node != null){
            notifySubscribers(node);
        }
        return node;

}
}
