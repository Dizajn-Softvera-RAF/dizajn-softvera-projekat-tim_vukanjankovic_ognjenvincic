package dsw.model.elements;

import dsw.repository.composite.ClassyNode;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public abstract class DiagramElement extends ClassyNode {
    protected Paint paint;
    protected Paint borderPaint;
    protected Stroke stroke;
    protected Paint textPaint;

    protected String name;
    @Getter
    @Setter
    protected float strokeWidth;

    public DiagramElement(String name, ClassyNode parent, float stroke, Paint paint, Paint borderPaint, Paint TextPaint) {
        super(name, parent);
        this.stroke = new BasicStroke(stroke);
        this.paint = paint;
        this.borderPaint = borderPaint;
        this.textPaint = TextPaint;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public Paint getBorderPaint() {
        return borderPaint;
    }

    public void setBorderPaint(Paint paint) {
        this.borderPaint = paint;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public Stroke getStroke() {
        return stroke;
    }

    public void setStroke(Stroke stroke) {
        this.stroke = stroke;
    }

    public Paint getTextPaint(){return textPaint;}

    public void setTextPaint(Paint paint){this.textPaint = paint;}
}

