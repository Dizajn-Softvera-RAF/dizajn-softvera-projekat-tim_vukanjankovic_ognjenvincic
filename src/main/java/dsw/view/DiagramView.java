package dsw.view;

import dsw.controller.MouseController;
import dsw.controller.MouseDragController;
import dsw.core.Config;
import dsw.model.elements.Agregacija;
import dsw.model.elements.ConnectionElement;
import dsw.model.elements.SelectedElement;
import dsw.model.helpers.ConnectionLine;
import dsw.observer.ISubscriber;
import dsw.repository.implementation.Diagram;
import dsw.view.painters.ConnectionPainter;
import dsw.view.painters.DevicePainter;
import dsw.view.painters.ElementPainter;
import dsw.view.painters.SelectedPainter;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Iterator;

@Getter
@Setter
public class DiagramView extends JPanel implements ISubscriber {

    private Diagram diagram;
    public DiagramView(Diagram diagram){
        this.diagram = diagram;

        setName(diagram.getName());
//        setCursor(new Cursor(Cursor.HAND_CURSOR));

        diagram.getModel().addSubscriber(this);

        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBackground(Color.WHITE);

        addMouseListener(new MouseController(diagram));
        addMouseMotionListener(new MouseDragController(diagram));
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // ZOOM I TRANSLATE
        AffineTransform affineTransform = AffineTransform.getTranslateInstance(diagram.getModel().getTransformX(), diagram.getModel().getTransformY());
        affineTransform.scale(diagram.getModel().getZoom(), diagram.getModel().getZoom());
        g2.transform(affineTransform);

        if (Config.AntiAliasing) {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }

        Iterator<DevicePainter> it = diagram.getModel().getDeviceIterator();
        int i = 0;
        for (ConnectionPainter ce : diagram.getModel().getVeze()) {

            // Dobijanje pocetne i krajnje tacke veze
            DevicePainter start = ((ConnectionElement)ce.getDevice()).getDevice1();
            DevicePainter end = ((ConnectionElement)ce.getDevice()).getDevice2();
            Point p1 = new Point((int)Math.floor(start.getDevice().getPosition().getX()), (int)Math.floor(start.getDevice().getPosition().getY()));
            Point p2 = new Point((int)Math.floor(end.getDevice().getPosition().getX()), (int)Math.floor(end.getDevice().getPosition().getY()));


            // Pravljenje nove linije na osnovu dobijenih tacaka
            DevicePainter line = new ConnectionPainter(new Agregacija(p1, new Dimension(p2.x, p2.y), ce.getDevice().getStrokeWidth(), ce.getDevice().getPaint(), ce.getDevice().getPaint(), ce.getDevice().getPaint(), ((ConnectionElement)ce.getDevice()).getConnectionLine()));
            line.getDevice().setSelected(ce.getDevice().isSelected());
            ((ConnectionElement)line.getDevice()).setDevice1(start);
            ((ConnectionElement)line.getDevice()).setDevice2(end);
            line.getDevice().setStrokeWidth(ce.getDevice().getStrokeWidth());
            diagram.getModel().getVeze().set(i, (ConnectionPainter) line);

            // Provera da li je linija selektovana
            if (diagram.getModel().getVeze().get(i).getDevice().isSelected()) {
                DevicePainter selected = new ConnectionPainter(new Agregacija(p1, new Dimension(p2.x, p2.y), ce.getDevice().getStrokeWidth()+2, new Color(0,0,0,0), Color.BLUE, Color.BLUE, new ConnectionLine(new Point(-1,-1), Color.gray, 1f)));
                selected.paint(g2, selected.getDevice());
            }
            line.paint(g2, line.getDevice());
            i++;
        }


        while(it.hasNext()){
            DevicePainter d = it.next();

            // Racunanje hitboxa pojma
            double x = d.getDevice().getPosition().getX();
            double y = d.getDevice().getPosition().getY();
            double width = d.getDevice().getDimension().getWidth();
            double height = d.getDevice().getDimension().getHeight();
            x = (x - width/2) - d.getDevice().getStrokeWidth()/2;
            y = (y - height/2) - d.getDevice().getStrokeWidth()/2;
            width = width + d.getDevice().getStrokeWidth();
            height = height + d.getDevice().getStrokeWidth();


            // Provera da li je pojam selektovan
            if (diagram.getModel().getSelectedElements().contains(d)) {
                DevicePainter selected = new SelectedPainter(new SelectedElement(new Point((int) x - 5, (int) y - 5), new Dimension((int) width + 10, (int) height + 10), d.getDevice().getStrokeWidth(), new Color(0,0,0,0), Color.BLUE, Color.black));
                selected.paint(g2, selected.getDevice());
            }

            // Crtanje debug hitboxa
            if (Config.DEBUG) {
                SelectedPainter hitbox = new SelectedPainter(new SelectedElement(new Point((int) x, (int) y), new Dimension((int) width, (int) height), 1f, new Color(0,0,0,0), Color.RED, Color.black));
                hitbox.paint(g2, hitbox.getDevice());
            }

            d.paint(g2, d.getDevice());

        }

        // Crtanje snapping linije X
        if (diagram.getModel().getAlignmentLineX().getDevice().getPosition().getX() != -1 || diagram.getModel().getAlignmentLineX().getDevice().getPosition().getY() != -1) {
            ElementPainter painter = diagram.getModel().getAlignmentLineX();
            painter.paint(g2, diagram.getModel().getAlignmentLineX().getDevice());
        }

        // Crtanje snapping linije Y
        if (diagram.getModel().getAlignmentLineY().getDevice().getPosition().getX() != -1 || diagram.getModel().getAlignmentLineY().getDevice().getPosition().getY() != -1) {
            ElementPainter painter = diagram.getModel().getAlignmentLineY();
            painter.paint(g2, diagram.getModel().getAlignmentLineY().getDevice());
        }

        // Crtanje veze koja se trenutno kreira
        if (diagram.getModel().getTempLine().getDevice().getPosition().getX() > -1 && diagram.getModel().getTempLine().getDevice().getPosition().getY() > -1) {
            ElementPainter painter = diagram.getModel().getTempLine();
            painter.paint(g2, diagram.getModel().getTempLine().getDevice());
        }

        // Crtanje selekcionog pravougaoinka
        if (diagram.getModel().getSelectionLine().getDevice().getPosition().getX() > -1 && diagram.getModel().getSelectionLine().getDevice().getPosition().getY() > -1) {
            ElementPainter painter = diagram.getModel().getSelectionLine();
            painter.paint(g2, diagram.getModel().getSelectionLine().getDevice());
        }

        // Crtanje okvira koji ne dozvoljava da se izadje iz mape
        if (Config.DEBUG) {
            DevicePainter b1 = new ConnectionPainter(new Agregacija(new Point(1,1), new Dimension(getSize().width-1,1), 1f, Color.RED, Color.RED, Color.black, new ConnectionLine(new Point(-1,-1), Color.RED, 1f)));
            DevicePainter b2 = new ConnectionPainter(new Agregacija(new Point(1,1), new Dimension(1,getSize().height-1), 1f, Color.RED, Color.RED, Color.black, new ConnectionLine(new Point(-1,-1), Color.RED, 1f)));
            DevicePainter b3 = new ConnectionPainter(new Agregacija(new Point(getSize().width-1,1), new Dimension(getSize().width-1,getSize().height-1), 1f, Color.RED, Color.RED, Color.black, new ConnectionLine(new Point(-1,-1), Color.RED, 1f)));
            DevicePainter b4 = new ConnectionPainter(new Agregacija(new Point(1,getSize().height-1), new Dimension(getSize().width-1,getSize().height-1), 1f, Color.RED, Color.RED, Color.black, new ConnectionLine(new Point(-1,-1), Color.RED, 1f)));

            b1.paint(g2, b1.getDevice());
            b2.paint(g2, b2.getDevice());
            b3.paint(g2, b3.getDevice());
            b4.paint(g2, b4.getDevice());
        }

        if (diagram.getModel().getPolygon() != null) {

            g2.setPaint(Color.BLUE);
            g2.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{2}, 0));
            g2.drawPolyline(diagram.getModel().getPolygon().xpoints, diagram.getModel().getPolygon().ypoints, diagram.getModel().getPolygon().npoints);
        }
    }


    @Override
    public void update(Object notification) {
        repaint();
    }
}
