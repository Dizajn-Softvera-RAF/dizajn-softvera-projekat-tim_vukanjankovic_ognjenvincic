package dsw.view.modals;

import dsw.controller.listeners.TemplateListener;
import dsw.core.Config;
import dsw.view.components.RoundBorder;
import dsw.view.components.TemplatePreview;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class TemplateModal extends JDialog implements Modal {

    private List<String> templates;
    private int width = 300;

    public TemplateModal(JFrame parent) {
        super(parent, true);
        loadTemplates();
        createGui();
    }

    private void createGui() {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(width*3 + 4*20, 400));
        JPanel gui = new JPanel();
        gui.setLayout(new BoxLayout(gui, BoxLayout.Y_AXIS));
        gui.setBorder(BorderFactory.createEmptyBorder(20,0,20,0));

        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row.add(getSpacer());


        for (int i = 0; i < templates.size(); i++) {

            JPanel panel = new JPanel();
            panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
            panel.setLayout(boxLayout);

            TemplatePreview image = new TemplatePreview(getTemplateImage(templates.get(i)));
            image.setPreferredSize(new Dimension(width, width*9/16-20));
            image.setMaximumSize(new Dimension(width, width*9/16-20));
            image.setBorder(BorderFactory.createLineBorder(Color.BLACK));


            JLabel name = new JLabel(templates.get(i));
            name.setAlignmentX(Component.CENTER_ALIGNMENT);
            name.setAlignmentY(Component.CENTER_ALIGNMENT);


            panel.setMaximumSize(new Dimension(width-20, width*9/16+20));
            panel.setPreferredSize(new Dimension(width-20, width*9/16+20));
            panel.setMinimumSize(new Dimension(width-20, width*9/16+20));
            panel.setSize(new Dimension(width-120, width*9/16+20));
            panel.revalidate();

            panel.add(image);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            panel.add(name);
            panel.setAlignmentX(Component.LEFT_ALIGNMENT);
            panel.setBackground(Color.WHITE);
            panel.setBorder(new RoundBorder(10));
            panel.addMouseListener(new TemplateListener(templates.get(i), this));

            row.add(panel);
            row.add(getSpacer());
            if (i % 3 == 2 && i != 0) {
                gui.add(row);
                gui.add(getSpacer());
                row = new JPanel(new FlowLayout(FlowLayout.LEFT));
                row.add(getSpacer());
            }
        }
        if (templates.size() % 3 != 0) {
            gui.add(row);
        }
        scrollPane.setViewportView(gui);
        add(scrollPane);
    }

    private JPanel getSpacer() {
        JPanel spacer = new JPanel();
        spacer.setPreferredSize(new Dimension(20, 20));
        spacer.setMaximumSize(new Dimension(20, 20));
        spacer.setMinimumSize(new Dimension(20, 20));
        spacer.setSize(new Dimension(20, 20));
        return spacer;
    }

    private void loadTemplates() {
        templates = new ArrayList<>();
        File folder = new File(Config.TEMPLATE_FOLDER);
        for (final File f : folder.listFiles()) {
            if (f.isFile() && f.getName().endsWith(".classyCraft")) {
                templates.add(f.getName().substring(0, f.getName().length() - 12));
            }
        }
    }

    private BufferedImage getTemplateImage(String templateName) {

        BufferedImage img = null;
        try {

            FileReader reader = new FileReader(Config.TEMPLATE_FOLDER + templateName + ".classyCraft");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(line);
            String base64 = (String) json.get("preview");
            bufferedReader.close();
            if (base64 != null) {
                byte[] bytes = Base64.getDecoder().decode(base64);
                img = javax.imageio.ImageIO.read(new ByteArrayInputStream(bytes));
                Image dimg = img.getScaledInstance(width-40, (width-40)*9/16, Image.SCALE_SMOOTH);

                return toBufferedImage(dimg);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return img;
    }

    private BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        return bimage;
    }

    @Override
    public void showModal(String title) {
        setTitle(title);
        pack();
        setLocationRelativeTo(getParent());
        setVisible(true);
    }
}