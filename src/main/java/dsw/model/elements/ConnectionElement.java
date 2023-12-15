package dsw.model.elements;

import dsw.model.helpers.ConnectionLine;
import dsw.repository.composite.ClassyNode;
import dsw.view.painters.InterclassPainter;

import java.awt.*;


public abstract class ConnectionElement extends DiagramElement{

    InterclassPainter device1;
    InterclassPainter device2;
    private Point position;
    private Dimension size;
    private ConnectionLine connectionLine;
    private boolean selected;


    public ConnectionElement(Point position, Dimension size, String name, ClassyNode parent, float stroke , Paint paint, Paint borderPaint, Paint TextPaint, ConnectionLine connectionLine) {
        super(name, parent, stroke, paint, borderPaint, TextPaint);
        this.connectionLine = connectionLine;
        this.position = position;
        this.size = size;
        this.selected = false;
    }

    public ConnectionLine getConnectionLine() {
        return connectionLine;
    }

    public void setConnectionLine(ConnectionLine connectionLine) {
        this.connectionLine = connectionLine;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public InterclassPainter getDevice1() {
        return device1;
    }

    public void setDevice1(InterclassPainter device1) {
        this.device1 = device1;
    }

    public InterclassPainter getDevice2() {
        return device2;
    }

    public void setDevice2(InterclassPainter device2) {
        this.device2 = device2;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Dimension getSize() {
        return size;
    }

    public void setSize(Dimension size) {
        this.size = size;
    }

}
