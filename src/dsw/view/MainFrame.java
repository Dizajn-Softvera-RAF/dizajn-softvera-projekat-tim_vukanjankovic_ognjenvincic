package dsw.view;


import dsw.controller.ActionManager;
import dsw.core.Utils;
import dsw.view.components.MyMenuBar;
import dsw.view.components.ToolBar;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private static MainFrame instance;
    private ActionManager actionManager;
    private JMenuBar menu;
    private JToolBar toolBar;

    private MainFrame(){
    }

    private void initalise(){
        actionManager = new ActionManager();
        initialiseGUI();
    }

    private void initialiseGUI(){
        setIconImage(Utils.loadIcon("/images/classy.png", 32, 32).getImage());
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        setSize(screenSize.width/2, screenSize.height/2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Classycraft");
        menu = new MyMenuBar();
        setJMenuBar(menu);
        toolBar = new ToolBar();
        add(toolBar, BorderLayout.NORTH);
    }


    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
            instance.initalise();
        }
        return instance;
    }


    public static void setInstance(MainFrame instance) {
        MainFrame.instance = instance;
    }

    public ActionManager getActionManager() {
        return actionManager;
    }

    public void setActionManager(ActionManager actionManager) {
        this.actionManager = actionManager;
    }

    public JMenuBar getMenu() {
        return menu;
    }

    public void setMenu(JMenuBar menu) {
        this.menu = menu;
    }

    public JToolBar getToolBar() {
        return toolBar;
    }

    public void setToolBar(JToolBar toolBar) {
        this.toolBar = toolBar;
    }

}
