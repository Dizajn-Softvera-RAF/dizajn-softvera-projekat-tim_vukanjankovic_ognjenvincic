package dsw.model.elements;

import dsw.view.painters.Shapes;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public abstract class Interclass extends DiagramElement {

    protected Dimension size;
    protected Point position;
    protected boolean selected;
    protected Dimension dimension;


    @Getter
    @Setter
    protected Shapes pojamShape;

    protected Interclass(Point position, Dimension size, float stroke, Paint paint, Paint borderPaint, Paint textPaint) {
        super(stroke, paint, borderPaint, textPaint);
        this.size = size;
        this.position = position;
        this.selected = false;
        this.dimension = size;
    }


    public void setSelected(boolean selected){this.selected = selected;
    }
    public boolean isSelected(){
        return selected;
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

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

}
