package dsw.view.painters;

import dsw.model.elements.DiagramElement;

import java.awt.*;

public abstract class ElementPainter {

    protected ElementPainter(DiagramElement element) {	}

    public abstract void paint(Graphics2D g, DiagramElement element);

    public abstract boolean elementAt(Point pos);
}
