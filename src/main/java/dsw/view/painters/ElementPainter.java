package dsw.view.painters;

import dsw.model.elements.DiagramElement;

import java.awt.*;

public abstract class ElementPainter {

    public DiagramElement device;
    protected ElementPainter(DiagramElement element) {
        this.device = element;
    }

    public abstract void paint(Graphics2D g, DiagramElement element);

    public abstract boolean elementAt(Point pos);


    public DiagramElement getDevice() {
        return device;
    }

    public void setDevice(DiagramElement device) {
        this.device = device;
    }
}


