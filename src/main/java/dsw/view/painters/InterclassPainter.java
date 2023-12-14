package dsw.view.painters;

import dsw.model.elements.Interclass;
import dsw.model.elements.DiagramElement;
import dsw.model.elements.RectangleElement;
import dsw.view.DiagramView;
import dsw.view.MainFrame;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class InterclassPainter extends ElementPainter {
    protected Shape shape;
    @Getter
    @Setter
    protected Interclass device;

    public InterclassPainter(Interclass device){

        super(device);
        this.device = device;
    }

    public void paint(Graphics2D g, DiagramElement element){
        device = (Interclass)element;
        if (!(this instanceof SelectedPainter)) {

            g.setFont(new Font("Verdana", Font.PLAIN, 12));
            if (((Interclass) element).getPojamShape() == Shapes.MAIN) {
                g.setFont(new Font("Verdana", Font.BOLD, 16));
            }
            ((InterclassPainter) this).resize(new Dimension(g.getFontMetrics().stringWidth(element.getName())+40, 50));


            DiagramView dv = (DiagramView) MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent();
            for (int i = 0; i < dv.getDiagram().getModel().getDiagramElements().size(); i++) {
                InterclassPainter rp = (InterclassPainter) dv.getDiagram().getModel().getDiagramElements().get(i);
                if (rp.getDevice().equals(element)) {

                    rp.resize(new Dimension(g.getFontMetrics().stringWidth(element.getName())+40, 50));
                    dv.getDiagram().getModel().getDiagramElements().set(i, this);
                    dv.getDiagram().getModel().getDiagramElements().get(i).getDevice().setDimension(new Dimension(g.getFontMetrics().stringWidth(element.getName())+40, 50));
                }
            }
        }


        g.setPaint(element.getBorderPaint());
        g.setStroke(element.getStroke());

        if (!(this instanceof SelectedPainter)) {
            g.setStroke(new BasicStroke(element.getStrokeWidth()));
        }


        if (this instanceof SelectedPainter) {
            g.setPaint(element.getBorderPaint());
            g.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                    0, new float[]{2}, 0));
        }

//        Interclass rectangle =  device;
//        shape = new Rectangle2D.Float(rectangle.getPosition().x-rectangle.getSize().width/2, rectangle.getPosition().y-rectangle.getSize().height/2, rectangle.getSize().width, rectangle.getSize().height);
        g.draw(getShape());
        g.setPaint(element.getPaint());

        g.fill(getShape());

        if (!(this instanceof SelectedPainter)){
            g.setPaint(Color.BLACK);

            int x = (int)device.getPosition().getX();
            x -= g.getFontMetrics().stringWidth(element.getName())/2;

            int y = (int)device.getPosition().getY();
            y += 5;
            g.setColor((Color)element.getTextPaint());

            g.drawString(device.getName(), x, y);
        }

    }

    private void updateShape(){
        Interclass rectangle = (Interclass) device;
        shape = new Rectangle2D.Float(rectangle.getPosition().x-rectangle.getSize().width/2, rectangle.getPosition().y-rectangle.getSize().height/2, rectangle.getSize().width, rectangle.getSize().height);
//        if (rectangle.getPojamShape() == Shapes.MAIN) {
//            shape = new Ellipse2D.Float(rectangle.getPosition().x-rectangle.getSize().width/2, rectangle.getPosition().y-rectangle.getSize().height/2, rectangle.getSize().width, rectangle.getSize().height);
//        }
//        if (rectangle.getPojamShape() == Shapes.ELLIPSE) {
//            shape = new Ellipse2D.Float(rectangle.getPosition().x-rectangle.getSize().width/2, rectangle.getPosition().y-rectangle.getSize().height/2, rectangle.getSize().width, rectangle.getSize().height);
//        } else if (rectangle.getPojamShape() == Shapes.RECTANGLE) {
//            shape = new Rectangle2D.Float(rectangle.getPosition().x-rectangle.getSize().width/2, rectangle.getPosition().y-rectangle.getSize().height/2, rectangle.getSize().width, rectangle.getSize().height);
//        } else if (rectangle.getPojamShape() == Shapes.ROUNDED_RECTANGLE) {
//            shape = new RoundRectangle2D.Float(rectangle.getPosition().x-rectangle.getSize().width/2, rectangle.getPosition().y-rectangle.getSize().height/2, rectangle.getSize().width, rectangle.getSize().height,20,20);
//        }
    }

    public void resize(Dimension size){
        device.setSize(size);
        updateShape();
    }
    public boolean elementAt(Point pos){
        return getShape().contains(pos);
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }


}
