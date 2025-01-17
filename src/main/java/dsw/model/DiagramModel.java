package dsw.model;

import dsw.model.elements.*;
import dsw.model.helpers.ConnectionLine;
import dsw.observer.IPublisher;
import dsw.observer.ISubscriber;
import dsw.view.DiagramView;
import dsw.view.painters.*;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

@Getter
@Setter
public class DiagramModel implements IPublisher {

    private static int count=0;
    private String name;
    protected DevicePainter selectionLine;
    private ArrayList<DevicePainter> selectedElements;
    private double zoom;
    private double transformX;
    private double transformY;

    protected ArrayList<DevicePainter> diagramElements;
    protected DevicePainter alignmentLineX;
    protected DevicePainter alignmentLineY;
    ArrayList<ISubscriber> subscribers;

    protected DevicePainter tempLine;

    protected ArrayList<ConnectionPainter> veze;

    protected DevicePainter selectionDebug;

    protected Polygon polygon;

    protected RectanglePainter tempRectangle;

    public DiagramModel() {
        init();
    }
    private void init(){
        subscribers = new ArrayList<>();
        selectionLine = new SelectedPainter(new SelectedElement(new Point(-1,-1), new Dimension(1,1), 1f, new Color(0,0,0,0), Color.blue, Color.black));
        selectedElements = new ArrayList<>();
        zoom = 1;
        transformX = 0;
        transformY = 0;
        diagramElements =new ArrayList<>();
        alignmentLineX = new AlignmentLinePainter(new AlignmentLineElement(new Point(-1,-1), new Dimension(1,1), 1f, Color.PINK, Color.PINK, Color.black));
        alignmentLineY = new AlignmentLinePainter(new AlignmentLineElement(new Point(-1,-1), new Dimension(1,1), 1f, Color.PINK, Color.PINK, Color.black));
        tempLine = new ConnectionPainter(new ConnectionElement(new Point(-1,-1), new Dimension(1,1), 1f, Color.GRAY, Color.GRAY, Color.black, new ConnectionLine(new Point(-1,-1), Color.GRAY, 1f)));
        veze = new ArrayList<>();
        selectionDebug = new SelectedPainter(new SelectedElement(new Point(5,5), new Dimension(110,100), 1f, new Color(0,0,0,0), Color.RED, Color.black));
        polygon = null;
    }
    private boolean connectionExists(DevicePainter start, DevicePainter end) {
        for (ConnectionPainter veza : veze) {
            if ( ((ConnectionElement)veza.getDevice()).getDevice1() == start && ((ConnectionElement)veza.getDevice()).getDevice2() == end) {
                return true;
            }
            if (((ConnectionElement)veza.getDevice()).getDevice1() == end && ((ConnectionElement)veza.getDevice()).getDevice2() == start) {
                return true;
            }
        }
        return false;
    }

    public void deleteElement(DevicePainter device) {
        diagramElements.remove(device);
        for (int i = 0; i < veze.size(); i++) {
            ((ConnectionElement)veze.get(i).getDevice()).getDevice2();
            if (((ConnectionElement)veze.get(i).getDevice()).getDevice2().equals(device) || ((ConnectionElement)veze.get(i).getDevice()).getDevice1().equals(device)) {
                veze.remove(i);
                i--;
            }
        }
        notifySubscribers(null);
    }

    public void deleteConnection(ConnectionElement connectionElement) {
        veze.remove(connectionElement);
        notifySubscribers(null);
    }

    public void addConnection(Point p) {
        if (p.getX() == p.getY()) return;
        ConnectionElement ce = new ConnectionElement(new Point(-1,-1), new Dimension(1,1), 10f, Color.black, Color.black, Color.black, new ConnectionLine(p, Color.GRAY, 1f));
        ce.setDevice1(diagramElements.get(p.x));
        ce.setDevice2(diagramElements.get(p.y));
        ce.setStrokeWidth(1f);

        if (!connectionExists(ce.getDevice1(), ce.getDevice2())) {
            veze.add(new ConnectionPainter(ce));
            notifySubscribers(null);
        }

    }

    public void setAlignmentLineX(Point p, boolean update) {
        alignmentLineX.getDevice().setPosition(p);
        ((AlignmentLinePainter) alignmentLineX).setPosition(p);
        if (update) {
            notifySubscribers(null);
        }
    }

    public void setAlignmentLineY(Point p, boolean update) {
        alignmentLineY.getDevice().setPosition(p);
        ((AlignmentLinePainter) alignmentLineY).setPosition(p);
        if (update) {
            notifySubscribers(null);
        }
    }

    public void setTempLine(Point p1, Point p2) {
        tempLine.getDevice().setPosition(p1);
        tempLine.getDevice().setSize(new Dimension(p2.x, p2.y));
        ((ConnectionPainter) tempLine).setPosition(p1, p2);
        notifySubscribers(null);
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
    public int getElementCount(){
        return diagramElements.size();
    }

    public Iterator<DevicePainter> getDeviceIterator(){
        return diagramElements.iterator();
    }

    public void addDiagramElements(DevicePainter device){
        if (device.getDevice().getStrokeWidth() == 0) {
            device.getDevice().setStrokeWidth(1f);
        }
        diagramElements.add(device);
        notifySubscribers(null);
    }

    public void importDiagramElements(DevicePainter device){
        diagramElements.add(device);
        notifySubscribers(null);
    }

    public void importVeza(ConnectionPainter device){

        veze.add(device);
        notifySubscribers(null);
    }

    public void setSelectionLine(Point p1, Dimension d1){

        selectionLine.getDevice().setPosition(p1);
        selectionLine.getDevice().setSize(d1);
        ((SelectedPainter) selectionLine).setPosition(p1, d1);
        notifySubscribers(null);

    }

    public void setSelectionDebug(Point p1, Dimension d1){

        selectionDebug.getDevice().setPosition(p1);
        selectionDebug.getDevice().setSize(d1);
        ((SelectedPainter) selectionDebug).setPosition(p1, d1);
        notifySubscribers(null);

    }

    public void clearSelecterElements(){
        selectedElements = new ArrayList<>();
        for (DevicePainter devicePainter : veze) {
            devicePainter.getDevice().setSelected(false);
        }
    }

    public void addSelectedElements(DevicePainter device){
        selectedElements.add(device);
    }


    @Override
    public void addSubscriber(ISubscriber sub) {
        subscribers.add(sub);
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        subscribers.remove(sub);
    }

    @Override
    public void notifySubscribers(Object notification) {
        for (ISubscriber sub : subscribers) {
            DiagramView view = (DiagramView) sub;
            if (view != null) {
                sub.update(notification);
            }
        }
    }

    public DiagramModel getClone(){

        DiagramModel model = new DiagramModel();



        for(DevicePainter devicePainter : diagramElements){

            RectangleElement rectanglePainter = new RectangleElement(
                    new Point(devicePainter.getDevice().getPosition().x, devicePainter.getDevice().getPosition().y),
                    new Dimension(devicePainter.getDevice().getDimension().width, devicePainter.getDevice().getDimension().height),
                    devicePainter.getDevice().getStrokeWidth(),
                    devicePainter.getDevice().getPaint(),
                    devicePainter.getDevice().getBorderPaint(),
                    devicePainter.getDevice().getTextPaint()
            );

            rectanglePainter.setPojamShape(devicePainter.getDevice().getPojamShape());
            rectanglePainter.setName(devicePainter.getDevice().getName());
            rectanglePainter.setStrokeWidth(devicePainter.getDevice().getStrokeWidth());
            model.addDiagramElements(new RectanglePainter(rectanglePainter));
        }



        for(DevicePainter devicePainter : veze){

            ConnectionElement connectionElement = new ConnectionElement(
                    new Point(0, 0),
                    new Dimension(1,1),
                    devicePainter.getDevice().getStrokeWidth(),
                    devicePainter.getDevice().getPaint(),
                    devicePainter.getDevice().getPaint(),
                    devicePainter.getDevice().getPaint(),
                    new ConnectionLine(new Point(0,0), Color.BLUE, 0)
            );

            connectionElement.setStrokeWidth(devicePainter.getDevice().getStrokeWidth());
            int index1 = diagramElements.indexOf(((ConnectionElement)devicePainter.getDevice()).getDevice1());
            int index2 = diagramElements.indexOf(((ConnectionElement)devicePainter.getDevice()).getDevice2());
            connectionElement.setDevice1(model.getDiagramElements().get(index1));
            connectionElement.setDevice2(model.getDiagramElements().get(index2));
            connectionElement.setSelected(devicePainter.getDevice().isSelected());
            model.importVeza(new ConnectionPainter(connectionElement));
        }
        for(int i = 0; i<selectedElements.size(); i++){
            int index = diagramElements.indexOf(selectedElements.get(i));
            if(index == -1)continue;
            model.addSelectedElements(model.getDiagramElements().get(index));
        }
        for(ISubscriber sub : subscribers){
            model.addSubscriber(sub);
        }

        model.setZoom(zoom);
        model.setTransformX(transformX);
        model.setTransformY(transformY);

        return model;
    }

}
