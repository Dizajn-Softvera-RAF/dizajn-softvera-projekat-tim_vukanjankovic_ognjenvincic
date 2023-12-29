package dsw.view;

import dsw.controller.MouseController;
import dsw.controller.MouseDragController;
import dsw.core.Config;
import dsw.model.elements.*;
import dsw.model.helpers.ConnectionLine;
import dsw.observer.ISubscriber;
import dsw.repository.implementation.Diagram;
import dsw.state.concrete.*;
import dsw.view.painters.*;
import dsw.view.resolver.ConnectionPointsResolver;
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
    private float[] dashPattern = {5.0f, 5.0f};
    private boolean shouldFill = true;

    public DiagramView(Diagram diagram) {
        this.diagram = diagram;

        setName(diagram.getName());

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

        Iterator<InterclassPainter> it = diagram.getModel().getDeviceIterator();
        int i = 0;
        for (ConnectionPainter ce : diagram.getModel().getVeze()) {
            // Dobijanje pocetne i krajnje tacke veze
            InterclassPainter start = (ce.getDevice()).getDevice1();
            InterclassPainter end = (ce.getDevice()).getDevice2();
            ConnectionPointsResolver resolver = null;
            if (ce instanceof KompozicijaPainter || ce instanceof AgregacijaPainter) {
                resolver = new ConnectionPointsResolver(end, start);
            } else {
                resolver = new ConnectionPointsResolver(start, end);
            }
            Point startPoint = resolver.calculateStart();
            Point endPoint = resolver.calculateEnd();
            ConnectionPainter line = null;

            if (ce instanceof AgregacijaPainter) {
                line = new AgregacijaPainter(new Agregacija(startPoint, new Dimension(endPoint.x, endPoint.y), "Agregacija", MainFrame.getInstance().getProjectView().getDiagram(), ce.getDevice().getStrokeWidth(), ce.getDevice().getPaint(), ce.getDevice().getPaint(), ce.getDevice().getPaint(), ((ConnectionElement) ce.getDevice()).getConnectionLine()));
                shouldFill = false;
            } else if (ce instanceof GeneralizacijaPainter) {
                line = new GeneralizacijaPainter(new Generalizacija(startPoint, new Dimension(endPoint.x, endPoint.y), "Generalizacija", MainFrame.getInstance().getProjectView().getDiagram(), ce.getDevice().getStrokeWidth(), ce.getDevice().getPaint(), ce.getDevice().getPaint(), ce.getDevice().getPaint(), ((ConnectionElement) ce.getDevice()).getConnectionLine()));
                shouldFill = true;
            } else if (ce instanceof KompozicijaPainter) {
                line = new KompozicijaPainter(new Kompozicija(startPoint, new Dimension(endPoint.x, endPoint.y), "Kompozicija", MainFrame.getInstance().getProjectView().getDiagram(), ce.getDevice().getStrokeWidth(), ce.getDevice().getPaint(), ce.getDevice().getPaint(), ce.getDevice().getPaint(), ((ConnectionElement) ce.getDevice()).getConnectionLine()));
                shouldFill = true;
            } else if (ce instanceof ZavisnostPainter) {
                line = new ZavisnostPainter(new Zavisnost(startPoint, new Dimension(endPoint.x, endPoint.y), "Zavisnost", MainFrame.getInstance().getProjectView().getDiagram(), ce.getDevice().getStrokeWidth(), ce.getDevice().getPaint(), ce.getDevice().getPaint(), ce.getDevice().getPaint(), ((ConnectionElement) ce.getDevice()).getConnectionLine()));
                Stroke dashed = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, dashPattern, 0);
                line.getDevice().setStroke(dashed);
                shouldFill = true;
            }
            line.getDevice().setSelected(ce.getDevice().isSelected());
            (line.getDevice()).setDevice1(start);
            (line.getDevice()).setDevice2(end);
            line.getDevice().setStrokeWidth(ce.getDevice().getStrokeWidth());
            diagram.getModel().getVeze().set(i, line);

//             Provera da li je linija selektovana
            if (diagram.getModel().getVeze().get(i).getDevice().isSelected()) {
                ConnectionPainter selected = new AgregacijaPainter(new Agregacija(startPoint, new Dimension(endPoint.x, endPoint.y), "Selektovan", MainFrame.getInstance().getProjectView().getDiagram(), ce.getDevice().getStrokeWidth() + 2, new Color(0, 0, 0, 0), Color.BLUE, Color.BLUE, new ConnectionLine(new Point(-1, -1), Color.gray, 1f))) {
                };
                selected.paint(g2, selected.getDevice(), shouldFill);
            }
            line.paint(g2, line.getDevice(), shouldFill);
            i++;
        }


        while (it.hasNext()) {
            InterclassPainter d = it.next();

            // Racunanje hitboxa pojma
            double x = d.getDevice().getPosition().getX();
            double y = d.getDevice().getPosition().getY();
            double width = d.getDevice().getDimension().getWidth();
            double height = d.getDevice().getDimension().getHeight();
            x = (x - width / 2) - d.getDevice().getStrokeWidth() / 2;
            y = (y - height / 2) - d.getDevice().getStrokeWidth() / 2;
            width = width + d.getDevice().getStrokeWidth();
            height = height + d.getDevice().getStrokeWidth();


            // Provera da li je pojam selektovan
            if (diagram.getModel().getSelectedElements().contains(d)) {
                InterclassPainter selected = new SelectedPainter(new SelectedElement(new Point((int) x - 5, (int) y - 5), new Dimension((int) width + 10, (int) height + 10), "Selektovan", MainFrame.getInstance().getProjectView().getDiagram(), d.getDevice().getStrokeWidth(), new Color(0, 0, 0, 0), Color.BLUE, Color.black));
                selected.paint(g2, selected.getDevice(), shouldFill);
            }

            // Crtanje debug hitboxa
            if (Config.DEBUG) {
                SelectedPainter hitbox = new SelectedPainter(new SelectedElement(new Point((int) x, (int) y), new Dimension((int) width, (int) height), "Selektovan", MainFrame.getInstance().getProjectView().getDiagram(), 1f, new Color(0, 0, 0, 0), Color.RED, Color.black));
                hitbox.paint(g2, hitbox.getDevice(), shouldFill);
            }

            d.paint(g2, d.getDevice(), shouldFill);

        }
//
//        // Crtanje snapping linije X
//        if (diagram.getModel().getAlignmentLineX().getDevice().getPosition().getX() != -1 || diagram.getModel().getAlignmentLineX().getDevice().getPosition().getY() != -1) {
//            ElementPainter painter = diagram.getModel().getAlignmentLineX();
//            painter.paint(g2, diagram.getModel().getAlignmentLineX().getDevice());
//        }
//
//        // Crtanje snapping linije Y
//        if (diagram.getModel().getAlignmentLineY().getDevice().getPosition().getX() != -1 || diagram.getModel().getAlignmentLineY().getDevice().getPosition().getY() != -1) {
//            ElementPainter painter = diagram.getModel().getAlignmentLineY();
//            painter.paint(g2, diagram.getModel().getAlignmentLineY().getDevice());
//        }

        // Crtanje veze koja se trenutno kreira
        if (diagram.getModel().getTempLine().getDevice().getPosition().getX() > -1 && diagram.getModel().getTempLine().getDevice().getPosition().getY() > -1) {
            ElementPainter painter = diagram.getModel().getTempLine();
            painter.paint(g2, diagram.getModel().getTempLine().getDevice(), shouldFill);

        }

        // Crtanje selekcionog pravougaoinka
        if (diagram.getModel().getSelectionLine().getDevice().getPosition().getX() > -1 && diagram.getModel().getSelectionLine().getDevice().getPosition().getY() > -1) {
            ElementPainter painter = diagram.getModel().getSelectionLine();
            painter.paint(g2, diagram.getModel().getSelectionLine().getDevice(), shouldFill);
        }

        // Crtanje okvira koji ne dozvoljava da se izadje iz diagrama
        if (Config.DEBUG) {
            ConnectionPainter b1 = new AgregacijaPainter(new Agregacija(new Point(1, 1), new Dimension(getSize().width - 1, 1), "Granica", MainFrame.getInstance().getProjectView().getDiagram(), 1f, Color.RED, Color.RED, Color.black, new ConnectionLine(new Point(-1, -1), Color.RED, 1f)));
            ConnectionPainter b2 = new AgregacijaPainter(new Agregacija(new Point(1, 1), new Dimension(1, getSize().height - 1), "Granica", MainFrame.getInstance().getProjectView().getDiagram(), 1f, Color.RED, Color.RED, Color.black, new ConnectionLine(new Point(-1, -1), Color.RED, 1f)));
            ConnectionPainter b3 = new AgregacijaPainter(new Agregacija(new Point(getSize().width - 1, 1), new Dimension(getSize().width - 1, getSize().height - 1), "Granica", MainFrame.getInstance().getProjectView().getDiagram(), 1f, Color.RED, Color.RED, Color.black, new ConnectionLine(new Point(-1, -1), Color.RED, 1f)));
            ConnectionPainter b4 = new AgregacijaPainter(new Agregacija(new Point(1, getSize().height - 1), new Dimension(getSize().width - 1, getSize().height - 1), "Granica", MainFrame.getInstance().getProjectView().getDiagram(), 1f, Color.RED, Color.RED, Color.black, new ConnectionLine(new Point(-1, -1), Color.RED, 1f)));

            b1.paint(g2, b1.getDevice(), shouldFill);
            b2.paint(g2, b2.getDevice(), shouldFill);
            b3.paint(g2, b3.getDevice(), shouldFill);
            b4.paint(g2, b4.getDevice(), shouldFill);
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
