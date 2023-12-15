package dsw.state.concrete;

import dsw.core.ApplicationFramework;
import dsw.core.Config;
import dsw.core.logger.MessageType;
import dsw.model.DiagramModel;
import dsw.model.elements.ConnectionElement;
import dsw.model.elements.Interclass;
import dsw.model.elements.Interfejs;
import dsw.model.elements.RectangleElement;
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

public class InterfejsState extends AbstractState implements State {
    private int retries = 0;


    @Override
    public void mouseClicked(MouseEvent e, Diagram diagram) {
        retries = 0;
        ClickedValue clickedValue = getClickedIndex(diagramPoint(e.getPoint(), diagram.getModel()), diagram);
        DiagramView view = (DiagramView) MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent();

        String name = "<<Interface>> \n Class " + diagram.getModel().getElementCount();
        Interfejs interfejs = null;
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
            interfejs=new Interfejs(new Point(view.getSize().width / 2, view.getSize().height / 2), new Dimension(74,60),
                    "Interfejs", MainFrame.getInstance().getProjectView().getDiagram(),2f, new Color(121, 207, 246), Color.black, Color.black);
        } else {
            interfejs=new Interfejs(new Point(x, y), new Dimension(74,50),
                    "Interfejs", MainFrame.getInstance().getProjectView().getDiagram(),2f, new Color(121, 207, 246), Color.black, Color.black);
        }

        interfejs.setName(name);
        diagram.getModel().addDiagramElements(new InterclassPainter(interfejs));

    }

    private Point getAvailableSpot(InterclassPainter e, DiagramModel model) {
        retries++;
        Interclass interfejs = (Interclass) e.getDevice();

        DiagramView view = (DiagramView) MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent();

        int x = (int) (e.getDevice().getPosition().getX()-37);
        int y = (int) (e.getDevice().getPosition().getY()-25);

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
        ((Interclass) clicked.getD().getDevice()).setPojamShape(Shapes.MAIN);

        bfsOrder(diagram.getModel(),  (RectanglePainter) clicked.getD());

        diagram.getModel().notifySubscribers(null);
    }

    private Queue<QueueValue> getConnectedDevices(InterclassPainter painter, DiagramModel model) {
        Queue<QueueValue> list = new LinkedList<>();
        for (ConnectionPainter connection : model.getVeze()) {
            if ((connection.getDevice()).getDevice1() == painter) {
                list.add(new QueueValue( (connection.getDevice()).getDevice2(),  (connection.getDevice()).getDevice1()  ));
            } else if ((connection.getDevice()).getDevice2() == painter) {
                list.add(new QueueValue( (connection.getDevice()).getDevice1(),  (connection.getDevice()).getDevice2()  ));
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

        RectanglePainter p = (RectanglePainter) tree.getRoot();
        RectangleElement e = (RectangleElement) p.getDevice();
        e.setPosition(new Point(view.getSize().width / 2, view.getSize().height / 2));

        for (Object t : tree.children) {
            Tree child = (Tree) t;
            ((RectanglePainter)child.getRoot()).getDevice().setPosition(getAvailableSpot(p, model));

            RectanglePainter c = (RectanglePainter) child.getRoot();
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
