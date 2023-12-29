package dsw.controller.listeners;

import dsw.core.Config;
import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class TemplateNameSetAction implements ActionListener {

    private JTextField tf;
    private JSONObject template;
    private JDialog jDialog;


    public TemplateNameSetAction(JSONObject template, JTextField textField, JDialog jDialog) {
        this.template = template;
        tf = textField;
        this.jDialog = jDialog;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String savepath = Config.TEMPLATE_FOLDER + tf.getText() + ".classyCraft";
            FileWriter myWriter = new FileWriter(savepath);
            myWriter.write(template.toJSONString());
            myWriter.close();
            System.out.println("Successfully saved template.");
        } catch (IOException ex) {
            System.out.println("An error occurred.");
            ex.printStackTrace();
        }
        jDialog.dispose();
    }
}