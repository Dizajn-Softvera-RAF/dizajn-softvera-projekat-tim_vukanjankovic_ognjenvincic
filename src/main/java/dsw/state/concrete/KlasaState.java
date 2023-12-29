package dsw.state.concrete;

import dsw.commands.AbstractCommand;
import dsw.commands.implementation.GuiCommand;
import dsw.core.ApplicationFramework;
import dsw.core.Config;
import dsw.core.logger.MessageType;
import dsw.model.DiagramModel;
import dsw.model.elements.*;
import dsw.model.helpers.ClickedValue;
import dsw.model.helpers.Tree;
import dsw.repository.implementation.Diagram;
import dsw.state.AbstractState;
import dsw.state.State;
import dsw.view.DiagramView;
import dsw.view.MainFrame;
import dsw.view.painters.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class KlasaState extends AbstractState implements State {
    private int retries = 0;


    @Override
    public void mouseClicked(MouseEvent e, Diagram diagram) {
        retries = 0;
        for (InterclassPainter painter : diagram.getModel().getDiagramElements()) {
            if (painter.getDevice().getPojamShape() == Shapes.MAIN && Config.SHAPE == Shapes.MAIN) {
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessageType.MAIN_ALREADY_EXISTS);
                return;
            }
        }
        ClickedValue clickedValue = getClickedIndex(diagramPoint(e.getPoint(), diagram.getModel()), diagram);
        DiagramView view = (DiagramView) MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent();

        String name = "Class " + diagram.getModel().getElementCount();
        DiagramModel oldModel = diagram.getModel().getClone();
        Klasa rectangle = null;
        int x, y;

        if (clickedValue != null && clickedValue.getType() == 0) {
            Point spot = getAvailableSpot((InterclassPainter) clickedValue.getD(), diagram.getModel());

            if (spot == null) {

                ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessageType.CANT_ADD);
                return;
            }
            x = spot.x;
            y = spot.y;
        } else {
            if (Config.SHAPE == Shapes.MAIN) {
                x = view.getSize().width / 2;
                y = view.getSize().height / 2;
            } else {
                x = diagramPoint(e.getPoint(), diagram.getModel()).x;
                y = diagramPoint(e.getPoint(), diagram.getModel()).y;
            }
        }

        if (Config.SHAPE == Shapes.MAIN) {
            rectangle=new Klasa(new Point(view.getSize().width / 2, view.getSize().height / 2), new Dimension(74,60),
                    "Klasa", MainFrame.getInstance().getProjectView().getDiagram(),2f, new Color(121, 207, 246), Color.black, Color.black, new ArrayList<>(), new ArrayList<>());
        } else {
            rectangle=new Klasa(new Point(x, y), new Dimension(74,50),
                    "Klasa", MainFrame.getInstance().getProjectView().getDiagram(),2f, new Color(121, 207, 246), Color.black, Color.black, new ArrayList<>(), new ArrayList<>());
        }

        rectangle.setName(name);
        diagram.getModel().addDiagramElements(new InterclassPainter(rectangle));
        AbstractCommand command = new GuiCommand(oldModel, diagram.getModel().getClone(), diagram);
        ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(command);

    }

    private Point getAvailableSpot(InterclassPainter e, DiagramModel model) {
        retries++;
        Interclass element = (Interclass) e.getDevice();

        DiagramView view = (DiagramView) MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent();

        int x = (int) (e.getDevice().getPosition().getX()-37);
        int y = (int) (e.getDevice().getPosition().getY()-25);

        for (InterclassPainter painter : model.getDiagramElements()) {

        }

        return new Point(x+37, y+25);
    }
    @Override
    public void mouseDragged(MouseEvent e, Diagram diagram) {

    }

    @Override
    public void mouseReleased(MouseEvent e, Diagram diagram) {
        if (!e.isPopupTrigger()) return;
        ClickedValue clicked = getClickedIndex(diagramPoint(e.getPoint(), diagram.getModel()), diagram);
        if (clicked == null) return;
        if (clicked.getType() != 0) return;
        for (InterclassPainter painter : diagram.getModel().getDiagramElements()) {
            if (painter.getDevice().getPojamShape() == Shapes.MAIN && painter != clicked.getD()) {
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessageType.MAIN_ALREADY_EXISTS);
                return;
            }
        }
        ((Interclass) clicked.getD().getDevice()).setPojamShape(Shapes.ELLIPSE);

        bfsOrder(diagram.getModel(), (InterclassPainter) clicked.getD());

        diagram.getModel().notifySubscribers(null);
    }

    private Queue<QueueValue> getConnectedDevices(InterclassPainter painter, DiagramModel model) {
        Queue<QueueValue> list = new LinkedList<>();
        for (ConnectionPainter connection : model.getVeze()) {
            if (((ConnectionElement)connection.getDevice()).getDevice1() == painter) {
                list.add(new QueueValue( ((ConnectionElement)connection.getDevice()).getDevice2(),  ((ConnectionElement)connection.getDevice()).getDevice1()  ));
            } else if (((ConnectionElement)connection.getDevice()).getDevice2() == painter) {
                list.add(new QueueValue( ((ConnectionElement)connection.getDevice()).getDevice1(),  ((ConnectionElement)connection.getDevice()).getDevice2()  ));
            }
        }
        return list;
    }
    private void draw(InterclassPainter parent, Tree tree, DiagramModel model) {
        for (Object t : tree.children) {
            Tree child = (Tree) t;

            InterclassPainter painter = (InterclassPainter) child.getRoot();

            painter.getDevice().setPosition(getAvailableSpot(parent, model));

            draw(painter, child, model);
        }
    }

    private int getNumberOfConnections(InterclassPainter root, DiagramModel model) {
        Queue<QueueValue> queue = getConnectedDevices(root, model);
        ArrayList visited = new ArrayList<InterclassPainter>();
        while (!queue.isEmpty()) {
            QueueValue qv = queue.poll();
            if (!visited.contains(qv.child)) {
                visited.add(qv.child);
                queue.addAll(getConnectedDevices(qv.child, model));
            }
        }
        return visited.size();
    }

    private void bfsOrder(DiagramModel model, InterclassPainter root) {

        Tree tree = new Tree(model, root, null);
        System.out.println(getNumberOfConnections(root, model));
        Queue<QueueValue> queue = getConnectedDevices(root, model);
        while (!queue.isEmpty() && tree.getSize() < getNumberOfConnections(root, model)) {

            QueueValue qv = queue.poll();
            qv.child.getDevice().setPosition(new Point(-100 ,-100));
            qv.parent.getDevice().setPosition(new Point(-100 ,-100));
            tree.addChild(qv.child, qv.parent);
            queue.addAll(getConnectedDevices(qv.child, model));

        }

        model.notifySubscribers(null);

        DiagramView view = (DiagramView) MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent();

        InterclassPainter p = (InterclassPainter) tree.getRoot();
        Interclass e = (Interclass) p.getDevice();
        e.setPosition(new Point(view.getSize().width / 2, view.getSize().height / 2));

        for (Object t : tree.children) {
            Tree child = (Tree) t;
            ((InterclassPainter)child.getRoot()).getDevice().setPosition(getAvailableSpot(p, model));

            InterclassPainter c = (InterclassPainter) child.getRoot();
            draw(c, child, model);


        }
    }

    private class QueueValue {
        InterclassPainter child;
        InterclassPainter parent;

        public QueueValue(InterclassPainter child, InterclassPainter parent) {
            this.child = child;
            this.parent = parent;
        }
    }
}
