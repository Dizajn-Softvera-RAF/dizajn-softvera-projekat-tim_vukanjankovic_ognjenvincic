package dsw.view;


import dsw.controller.ActionManager;
import dsw.controller.tree.ClassyTreeImpl;
import dsw.core.ApplicationFramework;
import dsw.core.Utils;
import dsw.observer.ISubscriber;
import dsw.repository.composite.NodeGenerator;
import dsw.view.components.MyMenuBar;
import dsw.view.components.TabbedPane;
import dsw.view.components.ToolBar;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
@Getter
@Setter
public class MainFrame extends JFrame implements ISubscriber {
    private static MainFrame instance;
    private ActionManager actionManager;
    private JMenuBar menu;
    private JToolBar toolBar;
    private ClassyTreeImpl classyTree;
    private TabbedPane tabbedPane;
    private ProjectView projectView;
    private NodeGenerator nodeGenerator;
    private JScrollPane scroll;



    private MainFrame(){
    }

    private void initalise(){
        actionManager = new ActionManager();
        classyTree = new ClassyTreeImpl();
        projectView = new ProjectView();
        nodeGenerator = new NodeGenerator();
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

        JTree projectExplorer = classyTree.generateTree(ApplicationFramework.getInstance().getClassyRepository().getProjectExplorer(), nodeGenerator);

        scroll=new JScrollPane(projectExplorer);
        scroll.setMinimumSize(new Dimension(200,150));
        JPanel prView = new JPanel();
        prView.setLayout(new BoxLayout (prView, BoxLayout.X_AXIS));
        prView.add(projectView);
        JPanel tool = new JPanel();
        tool.setLayout(new FlowLayout(FlowLayout.LEADING));
        prView.add(tool);
        JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,scroll,prView);
        getContentPane().add(split,BorderLayout.CENTER);
        split.setDividerLocation(250);
        split.setOneTouchExpandable(true);

    }


    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
            instance.initalise();
        }
        return instance;
    }


    public ActionManager getActionManager() {
        return actionManager;
    }

    @Override
    public void update(Object notification) {
    }
}
