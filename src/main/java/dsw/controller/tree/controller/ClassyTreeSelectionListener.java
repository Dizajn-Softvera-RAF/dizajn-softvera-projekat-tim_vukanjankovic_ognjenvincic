package dsw.controller.tree.controller;

import dsw.controller.tree.model.ClassyTreeItem;
import dsw.observer.IPublisher;
import dsw.observer.ISubscriber;
import dsw.repository.implementation.Diagram;
import dsw.repository.implementation.Package;
import dsw.repository.implementation.Project;
import dsw.view.MainFrame;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import java.util.ArrayList;
import java.util.List;

public class ClassyTreeSelectionListener implements TreeSelectionListener, IPublisher {

    List<ISubscriber> subs;
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        subs = new ArrayList<>();
        addSubscriber(MainFrame.getInstance().getProjectView());
        TreePath path = e.getPath();
        ClassyTreeItem treeItemSelected = (ClassyTreeItem) path.getLastPathComponent();
        if (treeItemSelected.getClassyNode() instanceof Project) {
            notifySubscribers(treeItemSelected.getClassyNode());
        }
        else if (treeItemSelected.getClassyNode() instanceof Diagram) {
            while(!(treeItemSelected.getClassyNode() instanceof Project || treeItemSelected.getClassyNode() instanceof Package))
                treeItemSelected = treeItemSelected.getClassyNode().getParent().getClassyTreeItem();
            if(treeItemSelected.getClassyNode() instanceof Package)
                notifySubscribers(treeItemSelected.getClassyNode().getParent());
            notifySubscribers(treeItemSelected.getClassyNode());
        }
        else if (treeItemSelected.getClassyNode() instanceof Package) {
            notifySubscribers(treeItemSelected.getClassyNode().getParent());
            notifySubscribers(treeItemSelected.getClassyNode());
        }

    }

    @Override
    public void addSubscriber(ISubscriber sub) {
        subs.add(sub);
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        subs.remove(sub);
    }

    @Override
    public void notifySubscribers(Object notification) {
        for(ISubscriber sub : subs){
            sub.update(notification);
        }
    }
}
