package dsw.model.helpers;

import dsw.model.DiagramModel;

import java.util.ArrayList;
import java.util.List;

public class Tree<DevicePainter> {

    DiagramModel model;
    public List<Tree> children;
    Tree parent;
    DevicePainter root;
    boolean drawn;



    public Tree(DiagramModel model, DevicePainter root, Tree parent) {
        this.model = model;
        this.children = new ArrayList<>();
        this.root = root;
        this.parent = parent;
        this.drawn = false;
    }


    public void addChild(DevicePainter child, DevicePainter parent) {
        Tree root = this;
        while (root.parent != null) root = root.parent;
        if (isVisited(child, root)) return;
        Tree node = findNode(parent);
        if (node != null) {
//            System.out.println("Adding child " + ((RectanglePainter)child).getDevice().getName() + " to parent " + ((RectanglePainter)parent).getDevice().getName());
            node.children.add(new Tree(model, child, node));
        }
    }

    private Tree findNode(DevicePainter node) {
        if (this.root == node) return this;
        for (Tree child : children) {
            Tree found = child.findNode(node);
            if (found != null) return found;
        }
        return null;
    }

    public DevicePainter getRoot() {
        return root;
    }

    private boolean isVisited(DevicePainter child, Tree tree) {

        if (tree.getRoot().equals(child)) return true;

        for (Object t : tree.children) {
            boolean visited = isVisited(child, (Tree) t);
            if (visited) return true;
        }
        return false;
    }

    public int getSize() {
        int size = 1;
        for (Tree t : children) {
            size += t.getSize();
        }
        return size;
    }
}