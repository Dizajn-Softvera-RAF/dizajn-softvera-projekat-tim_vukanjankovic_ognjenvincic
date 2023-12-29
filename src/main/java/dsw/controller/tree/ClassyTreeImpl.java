package dsw.controller.tree;

import dsw.commands.AbstractCommand;
import dsw.commands.implementation.DiagramCommand;
import dsw.controller.tree.model.ClassyTreeItem;
import dsw.controller.tree.view.ClassyTreeView;
import dsw.core.ApplicationFramework;
import dsw.core.logger.MessageType;
import dsw.model.elements.*;
import dsw.model.elements.Enum;
import dsw.observer.ISubscriber;
import dsw.repository.composite.ClassyNode;
import dsw.repository.composite.ClassyNodeComposite;
import dsw.repository.composite.NodeGenerator;
import dsw.repository.implementation.Diagram;
import dsw.repository.implementation.NodeType;
import dsw.repository.implementation.Package;
import dsw.repository.implementation.Project;
import dsw.repository.implementation.ProjectExplorer;
import dsw.view.MainFrame;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;


@Getter
@Setter
public class ClassyTreeImpl implements ClassyTree, ISubscriber {

    private ClassyTreeView treeView;
    private DefaultTreeModel treeModel;
    private NodeGenerator nodeGenerator;
    private ClassyTreeItem root;


    @Override
    public ClassyTreeView generateTree(ProjectExplorer projectExplorer, NodeGenerator nodeGenerator) {
        this.nodeGenerator = nodeGenerator;
        root = new ClassyTreeItem(projectExplorer);
        treeModel = new DefaultTreeModel(root);
        treeView = new ClassyTreeView(treeModel);

        return treeView;
    }

    @Override
    public ClassyNode addChild(ClassyTreeItem parent, String name) {
        ClassyNode node = null;

        if (parent.getClassyNode() instanceof ProjectExplorer) {
            node = nodeGenerator.generateNode(NodeType.Project, parent);

        }

        if (parent.getClassyNode() instanceof Project) {
            ProjectExplorer pe = (ProjectExplorer) parent.getClassyNode().getParent();
            if(!pe.getChildren().contains(parent.getClassyNode())) {
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessageType.NODE_NOT_SELECTED);
                return node;
            }

            node = nodeGenerator.generateNode(NodeType.Diagram, parent);
        }

        if (parent.getClassyNode() instanceof Diagram) {
            Project pe = (Project) parent.getClassyNode().getParent();
            if (!pe.getChildren().contains(parent.getClassyNode())) {
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessageType.NODE_NOT_SELECTED);
            }

        }

        if (parent.getClassyNode() instanceof Package) {
            Package pcg = (Package) parent.getClassyNode().getParent();
            if (!pcg.getChildren().contains(parent.getClassyNode())) {
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessageType.NODE_NOT_SELECTED);
            }
            node = nodeGenerator.generateNode(NodeType.Diagram, parent);
        }
        if(node != null){
            AbstractCommand command = new DiagramCommand(parent, node.getClassyTreeItem());
            ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(command);
        }


        return node;
    }

    @Override
    public void addChild(ClassyTreeItem parent, NodeType child) {
        ClassyNode node = null;

        if (parent.getClassyNode() instanceof ProjectExplorer) {
            node = nodeGenerator.generateNode(child, parent);

        }

        else if (parent.getClassyNode() instanceof Project) {
            ProjectExplorer pe = (ProjectExplorer) parent.getClassyNode().getParent();
            if(!pe.getChildren().contains(parent.getClassyNode())) {
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessageType.NODE_NOT_SELECTED);
                return;
            }

            node = nodeGenerator.generateNode(child, parent);
        }

        else if (parent.getClassyNode() instanceof Diagram) {
            Project pe = (Project) parent.getClassyNode().getParent();
            if (!pe.getChildren().contains(parent.getClassyNode())) {
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessageType.NODE_NOT_SELECTED);
            }

        }

        else if (parent.getClassyNode() instanceof Package) {
            Project p = (Project) parent.getClassyNode().getParent();
            if (!p.getChildren().contains(parent.getClassyNode())) {
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessageType.NODE_NOT_SELECTED);
            }
            node = nodeGenerator.generateNode(child, parent);
        }else if (parent.getClassyNode() instanceof Klasa) {
            Diagram p = (Diagram) parent.getClassyNode().getParent();
            if (!p.getChildren().contains(parent.getClassyNode())) {
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessageType.NODE_NOT_SELECTED);
            }
            node = nodeGenerator.generateNode(child, parent);
        }else if (parent.getClassyNode() instanceof Interfejs) {
            Diagram p = (Diagram) parent.getClassyNode().getParent();
            if (!p.getChildren().contains(parent.getClassyNode())) {
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessageType.NODE_NOT_SELECTED);
            }
            node = nodeGenerator.generateNode(child, parent);
        }else if (parent.getClassyNode() instanceof Enum) {
            Diagram p = (Diagram) parent.getClassyNode().getParent();
            if (!p.getChildren().contains(parent.getClassyNode())) {
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessageType.NODE_NOT_SELECTED);
            }
            node = nodeGenerator.generateNode(child, parent);
        }else if (parent.getClassyNode() instanceof Agregacija) {
            Diagram p = (Diagram) parent.getClassyNode().getParent();
            if (!p.getChildren().contains(parent.getClassyNode())) {
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessageType.NODE_NOT_SELECTED);
            }
            node = nodeGenerator.generateNode(child, parent);
        }else if (parent.getClassyNode() instanceof Generalizacija) {
            Diagram p = (Diagram) parent.getClassyNode().getParent();
            if (!p.getChildren().contains(parent.getClassyNode())) {
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessageType.NODE_NOT_SELECTED);
            }
            node = nodeGenerator.generateNode(child, parent);
        }else if (parent.getClassyNode() instanceof Kompozicija) {
            Diagram p = (Diagram) parent.getClassyNode().getParent();
            if (!p.getChildren().contains(parent.getClassyNode())) {
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessageType.NODE_NOT_SELECTED);
            }
            node = nodeGenerator.generateNode(child, parent);
        }else if (parent.getClassyNode() instanceof Zavisnost) {
            Diagram p = (Diagram) parent.getClassyNode().getParent();
            if (!p.getChildren().contains(parent.getClassyNode())) {
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessageType.NODE_NOT_SELECTED);
            }
            node = nodeGenerator.generateNode(child, parent);
        }

    }

    @Override
    public ClassyTreeItem getSelectedNode() {
        return (ClassyTreeItem) treeView.getLastSelectedPathComponent();
    }

    @Override
    public void removeChild(ClassyTreeItem parent) {
        if (parent.getClassyNode() instanceof ProjectExplorer) {
        }

        if (parent.getClassyNode() instanceof Project) {
            parent.removeFromParent();
            treeView.expandPath(treeView.getSelectionPath());
            SwingUtilities.updateComponentTreeUI(treeView);
            ((ProjectExplorer) parent.getClassyNode().getParent()).deleteChild((Project) parent.getClassyNode());
        }

        if (parent.getClassyNode() instanceof Diagram) {
            parent.removeFromParent();
            ((ClassyNodeComposite) parent.getClassyNode().getParent()).deleteChild(parent.getClassyNode());
            treeView.expandPath(treeView.getSelectionPath());
            SwingUtilities.updateComponentTreeUI(treeView);
        }

        if (parent.getClassyNode() instanceof Package) {
            parent.removeFromParent();
            ((ClassyNodeComposite) parent.getClassyNode().getParent()).deleteChild(parent.getClassyNode());
            treeView.expandPath(treeView.getSelectionPath());
            SwingUtilities.updateComponentTreeUI(treeView);
        }
        if (parent.getClassyNode() instanceof Klasa) {
            parent.removeFromParent();
            ((ClassyNodeComposite) parent.getClassyNode().getParent()).deleteChild(parent.getClassyNode());
            treeView.expandPath(treeView.getSelectionPath());
            SwingUtilities.updateComponentTreeUI(treeView);
        }
        if (parent.getClassyNode() instanceof Interfejs) {
            parent.removeFromParent();
            ((ClassyNodeComposite) parent.getClassyNode().getParent()).deleteChild(parent.getClassyNode());
            treeView.expandPath(treeView.getSelectionPath());
            SwingUtilities.updateComponentTreeUI(treeView);
        }if (parent.getClassyNode() instanceof Enum) {
            parent.removeFromParent();
            ((ClassyNodeComposite) parent.getClassyNode().getParent()).deleteChild(parent.getClassyNode());
            treeView.expandPath(treeView.getSelectionPath());
            SwingUtilities.updateComponentTreeUI(treeView);
        }if (parent.getClassyNode() instanceof Agregacija) {
            parent.removeFromParent();
            ((ClassyNodeComposite) parent.getClassyNode().getParent()).deleteChild(parent.getClassyNode());
            treeView.expandPath(treeView.getSelectionPath());
            SwingUtilities.updateComponentTreeUI(treeView);
        }if (parent.getClassyNode() instanceof Kompozicija) {
            parent.removeFromParent();
            ((ClassyNodeComposite) parent.getClassyNode().getParent()).deleteChild(parent.getClassyNode());
            treeView.expandPath(treeView.getSelectionPath());
            SwingUtilities.updateComponentTreeUI(treeView);
        }if (parent.getClassyNode() instanceof Zavisnost) {
            parent.removeFromParent();
            ((ClassyNodeComposite) parent.getClassyNode().getParent()).deleteChild(parent.getClassyNode());
            treeView.expandPath(treeView.getSelectionPath());
            SwingUtilities.updateComponentTreeUI(treeView);
        }if (parent.getClassyNode() instanceof Generalizacija) {
            parent.removeFromParent();
            ((ClassyNodeComposite) parent.getClassyNode().getParent()).deleteChild(parent.getClassyNode());
            treeView.expandPath(treeView.getSelectionPath());
            SwingUtilities.updateComponentTreeUI(treeView);
        }


    }

    @Override
    public void updateNodeName(ClassyTreeItem node, String name) {
        node.setName(name);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public boolean projectExists(ClassyTreeItem node) {
        if (node == null) return false;
        if (node.getClassyNode() instanceof Project) {
            ProjectExplorer pe = (ProjectExplorer) node.getClassyNode().getParent();
            return pe.getChildren().contains(node.getClassyNode());
        }
        if (node.getClassyNode() instanceof Diagram) {
            ProjectExplorer pe = (ProjectExplorer) node.getClassyNode().getParent().getParent();
            return pe.getChildren().contains(node.getClassyNode().getParent());
        }
        return false;
    }

    @Override
    public boolean diagramExists(ClassyTreeItem node) {
        if (node == null) return false;
        if (node.getClassyNode() instanceof Diagram) {
            Project p = (Project) node.getClassyNode().getParent();
            return p.getChildren().contains(node.getClassyNode());
        }
        return false;
    }
    @Override
    public boolean packageExists(ClassyTreeItem node) {
        if (node == null) return false;
        if (node.getClassyNode() instanceof Package) {
            Project pe = (Project) node.getClassyNode().getParent();
            return pe.getChildren().contains(node.getClassyNode());
        }
        if (node.getClassyNode() instanceof Diagram) {
            Project pe = (Project) node.getClassyNode().getParent().getParent();
            return pe.getChildren().contains(node.getClassyNode().getParent());
        }

        return false;
    }

    @Override
    public void deselect() {
        treeView.clearSelection();
    }

    @Override
    public void update(Object notification) {

        ClassyTreeItem selected = MainFrame.getInstance().getClassyTree().getSelectedNode();

        if (notification instanceof Project) {
            ClassyNode node = (ClassyNode) notification;

            if (selected == null) {
                selected = root;
            }

            selected.add(new ClassyTreeItem(node));
            ((ClassyNodeComposite) selected.getClassyNode()).addChild((Project) node);
            treeView.expandPath(treeView.getSelectionPath());
            SwingUtilities.updateComponentTreeUI(treeView);
        }
        else if (notification instanceof Diagram) {

            ClassyNode node = (ClassyNode) notification;

            if (selected == null) {
                selected = node.getClassyTreeItem();
            }

            selected.add(new ClassyTreeItem(node));
            ((ClassyNodeComposite) selected.getClassyNode()).addChild(node);
            treeView.expandPath(treeView.getSelectionPath());
            SwingUtilities.updateComponentTreeUI(treeView);
        }
        else if (notification instanceof Package) {

            ClassyNode node = (ClassyNode) notification;

            if (selected == null) {
                selected = node.getClassyTreeItem();
            }

            selected.add(new ClassyTreeItem(node));
            ((ClassyNodeComposite) selected.getClassyNode()).addChild(node);
            treeView.expandPath(treeView.getSelectionPath());
            SwingUtilities.updateComponentTreeUI(treeView);
        }
        else if (notification instanceof Klasa) {

            ClassyNode node = (ClassyNode) notification;

            if (selected == null) {
                selected = node.getClassyTreeItem();
            }

            selected.add(new ClassyTreeItem(node));
            ((ClassyNodeComposite) selected.getClassyNode()).addChild(node);
            treeView.expandPath(treeView.getSelectionPath());
            SwingUtilities.updateComponentTreeUI(treeView);
        }else if (notification instanceof Interfejs) {

            ClassyNode node = (ClassyNode) notification;

            if (selected == null) {
                selected = node.getClassyTreeItem();
            }

            selected.add(new ClassyTreeItem(node));
            ((ClassyNodeComposite) selected.getClassyNode()).addChild(node);
            treeView.expandPath(treeView.getSelectionPath());
            SwingUtilities.updateComponentTreeUI(treeView);
        }else if (notification instanceof Enum) {

            ClassyNode node = (ClassyNode) notification;

            if (selected == null) {
                selected = node.getClassyTreeItem();
            }

            selected.add(new ClassyTreeItem(node));
            ((ClassyNodeComposite) selected.getClassyNode()).addChild(node);
            treeView.expandPath(treeView.getSelectionPath());
            SwingUtilities.updateComponentTreeUI(treeView);
        }else if (notification instanceof Agregacija) {

            ClassyNode node = (ClassyNode) notification;

            if (selected == null) {
                selected = node.getClassyTreeItem();
            }

            selected.add(new ClassyTreeItem(node));
            ((ClassyNodeComposite) selected.getClassyNode()).addChild(node);
            treeView.expandPath(treeView.getSelectionPath());
            SwingUtilities.updateComponentTreeUI(treeView);
        }else if (notification instanceof Generalizacija) {

            ClassyNode node = (ClassyNode) notification;

            if (selected == null) {
                selected = node.getClassyTreeItem();
            }

            selected.add(new ClassyTreeItem(node));
            ((ClassyNodeComposite) selected.getClassyNode()).addChild(node);
            treeView.expandPath(treeView.getSelectionPath());
            SwingUtilities.updateComponentTreeUI(treeView);
        }else if (notification instanceof Kompozicija) {

            ClassyNode node = (ClassyNode) notification;

            if (selected == null) {
                selected = node.getClassyTreeItem();
            }

            selected.add(new ClassyTreeItem(node));
            ((ClassyNodeComposite) selected.getClassyNode()).addChild(node);
            treeView.expandPath(treeView.getSelectionPath());
            SwingUtilities.updateComponentTreeUI(treeView);
        }else if (notification instanceof Zavisnost) {

            ClassyNode node = (ClassyNode) notification;

            if (selected == null) {
                selected = node.getClassyTreeItem();
            }

            selected.add(new ClassyTreeItem(node));
            ((ClassyNodeComposite) selected.getClassyNode()).addChild(node);
            treeView.expandPath(treeView.getSelectionPath());
            SwingUtilities.updateComponentTreeUI(treeView);
        }

    }
}
