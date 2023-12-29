package dsw.core.serializer;

import dsw.controller.tree.model.ClassyTreeItem;
import dsw.core.Utils;
import dsw.model.DiagramModel;
import dsw.model.elements.*;
import dsw.model.helpers.ConnectionLine;
import dsw.repository.composite.ClassyNode;
import dsw.repository.implementation.Diagram;
import dsw.repository.implementation.Project;
import dsw.view.MainFrame;
import dsw.view.modals.Modal;
import dsw.view.modals.TemplateNameModal;
import dsw.view.painters.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.tree.TreePath;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Serializer {

    private static String colorStringify(Paint p){
        Color c = (Color) p;
        return c.getRed() + "," + c.getGreen() + "," + c.getBlue();
    }
    private static Color colorParse(String s){
        String[] rgb = s.split(",");
        return new Color(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2]));
    }


    private static int getIndex(ArrayList<InterclassPainter> elements, ElementPainter device){
        for (int i = 0; i < elements.size(); i++){
            if (elements.get(i).equals(device)) return i;
        }
        return -1;
    }

    public static void importProject(String path) {
        Utils.IMPORTING = true;
        // Read file and create project

        JSONObject json;
        BufferedReader bufferedReader = null;
        try {
            FileReader reader = new FileReader(path);
            bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();
            JSONParser parser = new JSONParser();
            json = (JSONObject) parser.parse(line);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


        ClassyTreeItem treeRoot = MainFrame.getInstance().getClassyTree().getRoot();

        MainFrame.getInstance().getClassyTree().getTreeView().setSelectionPath(new TreePath(treeRoot.getPath()));

        ClassyNode projectNode = MainFrame.getInstance().getClassyTree().addChild(treeRoot, json.get("name").toString());

        MainFrame.getInstance().getClassyTree().getTreeView().setSelectionPath(new TreePath(projectNode.getClassyTreeItem().getPath()));

        Project p = (Project) projectNode;
        try {
            p.setAutor(json.get("autor").toString());
        } catch (NullPointerException e){
            p.setAutor("");
        }
        p.setSavePath(path);

        JSONArray diagrami = (JSONArray) json.get("diagrami");
        for (int i = 0; i < diagrami.size(); i++) {
            JSONObject diagram = (JSONObject) diagrami.get(i);
            ClassyNode diagramNode = MainFrame.getInstance().getClassyTree().addChild(projectNode.getClassyTreeItem(), diagram.get("name").toString());
            Diagram m = (Diagram) diagramNode;
            JSONArray elements = (JSONArray) diagram.get("pojmovi");

            for (int j = 0; j < elements.size(); j++) {
                JSONObject element = (JSONObject) elements.get(j);

                Interclass re = new Interclass(
                        new Point(Integer.parseInt(element.get("x").toString()), Integer.parseInt(element.get("y").toString())),
                        new Dimension(Integer.parseInt(element.get("width").toString()), Integer.parseInt(element.get("height").toString())),
                        "ime", MainFrame.getInstance().getProjectView().getDiagram(),
                        Float.parseFloat(element.get("stroke_width").toString()),
                        colorParse(element.get("fill").toString()),
                        colorParse(element.get("stroke_color").toString()),
                        colorParse(element.get("text_color").toString())
                ) {
                };

                re.setStrokeWidth(Float.parseFloat(element.get("stroke_width").toString()));
                re.setName(element.get("text").toString());

                m.getModel().importDiagramElements(new InterclassPainter(re));
            }

            JSONArray veze = (JSONArray) diagram.get("veze");

            for (int j = 0; j < veze.size(); j++) {
                JSONObject veza = (JSONObject) veze.get(j);

                ConnectionElement ce = new ConnectionElement(
                        new Point(0, 0),
                        new Dimension(0, 0),
                        "ime", MainFrame.getInstance().getProjectView().getDiagram(),
                        Float.parseFloat(veza.get("stroke_width").toString()),
                        colorParse(veza.get("stroke_color").toString()),
                        colorParse(veza.get("stroke_color").toString()),
                        colorParse(veza.get("stroke_color").toString()),
                        new ConnectionLine(new Point(0, 0), Color.CYAN, Float.parseFloat(veza.get("stroke_width").toString()))
                ) {
                };
                ce.setStrokeWidth(Float.parseFloat(veza.get("stroke_width").toString()));
                ce.setDevice1(m.getModel().getDiagramElements().get(Integer.parseInt(veza.get("start").toString())));
                ce.setDevice2(m.getModel().getDiagramElements().get(Integer.parseInt(veza.get("end").toString())));

//                if(ce instanceof Agregacija){
//                    m.getModel().importVeza(new AgregacijaPainter(ce));
//                }if(ce instanceof Generalizacija){
//                    m.getModel().importVeza(new GeneralizacijaPainter(ce));
//                }if(ce instanceof Zavisnost){
//                    m.getModel().importVeza(new ZavisnostPainter(ce));
//                }if(ce instanceof Kompozicija){
//                    m.getModel().importVeza(new KompozicijaPainter(ce));
//                }
                 m.getModel().importVeza(new GeneralizacijaPainter(ce));

            }

            p.addChild(m);
        }

        Utils.IMPORTING = false;
    }

    public static void saveFile(Project project, boolean template) {
        JSONObject json = new JSONObject();
        json.put("name", project.getName());
        json.put("autor", project.getAutor());


        JSONArray diagrami = new JSONArray();

        for (ClassyNode node : project.getChildren()) {
            if (node instanceof Diagram) {
                Diagram diagram = (Diagram) node;
                DiagramModel model = diagram.getModel();

                JSONObject diagramJson = new JSONObject();
                JSONArray pojmovi = new JSONArray();
                JSONArray veze = new JSONArray();

                diagramJson.put("name", diagram.getName());

                for (InterclassPainter pojam : model.getDiagramElements()) {
                    Interclass modelPojma =  pojam.getDevice();
                    JSONObject p = new JSONObject();
                    p.put("text", modelPojma.getName());
                    p.put("x", modelPojma.getPosition().x);
                    p.put("y", modelPojma.getPosition().y);
                    p.put("width", modelPojma.getSize().width);
                    p.put("height", modelPojma.getSize().height);
                    p.put("fill", colorStringify(modelPojma.getPaint()));
                    p.put("stroke_color", colorStringify(modelPojma.getBorderPaint()));
                    p.put("text_color", colorStringify(modelPojma.getTextPaint()));
                    p.put("stroke_width", modelPojma.getStrokeWidth());
                    pojmovi.add(p);
                }

                for (ConnectionPainter veza : model.getVeze()) {
                    ConnectionElement modelVeze =  veza.getDevice();
                    JSONObject v = new JSONObject();
                    v.put("start", getIndex(model.getDiagramElements(), modelVeze.getDevice1()));
                    v.put("end", getIndex(model.getDiagramElements(), modelVeze.getDevice2()));
                    v.put("stroke_color", colorStringify(modelVeze.getBorderPaint()));
                    v.put("stroke_width", modelVeze.getStrokeWidth());
                    veze.add(v);
                }

                diagramJson.put("pojmovi", pojmovi);
                diagramJson.put("veze", veze);

                diagrami.add(diagramJson);
            }


        }

        json.put("diagrami", diagrami);


        if (template) {
            json.put("preview", Utils.getBase64Image());
            Modal modal = new TemplateNameModal(MainFrame.getInstance(), json);
            modal.showModal("Unesite naziv šablona");
        } else {
            try {
                String savepath = project.getSavePath();
                if (!savepath.endsWith(".classyCraft")) savepath += ".classyCraft";
                FileWriter myWriter = new FileWriter(savepath);
                myWriter.write(json.toJSONString());
                myWriter.close();

                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }

    }

}
