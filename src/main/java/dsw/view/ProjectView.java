package dsw.view;

import dsw.observer.ISubscriber;
import dsw.repository.implementation.Diagram;
import dsw.state.StateManager;
import dsw.view.components.TabbedPane;
import dsw.repository.implementation.Package;
import dsw.repository.implementation.Project;
import lombok.Getter;
import lombok.Setter;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@Getter
@Setter
public class ProjectView extends JPanel implements ISubscriber {

    private JLabel imeProjekta, autor;
    private Project project;
    private Package pckg;
    private Diagram diagram;
    private StateManager stateManager;
    private TabbedPane tabbedPane;


    ProjectView(){
        stateManager = new StateManager();
        setLayout(new BoxLayout (this, BoxLayout.Y_AXIS));
        if (imeProjekta == null) imeProjekta = new JLabel();
        if (autor == null) autor = new JLabel();
        JPanel info = new JPanel();
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));

        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel a = new JPanel(new FlowLayout(FlowLayout.LEFT));

        p.add(new JLabel("Projekat: "));
        p.add(imeProjekta);

        a.add(new JLabel("Autor: "));
        a.add(autor);

        info.add(p);
        info.add(a);
        info.setMaximumSize(new Dimension(Integer.MAX_VALUE,1));

        tabbedPane = new TabbedPane();
        tabbedPane.addChangeListener(e ->
                MainFrame.getInstance().getPallete().setSelectedButton(getStateManager().getCurrentState())
        );

        add(info);
        JPanel down = new JPanel();
        down.setLayout(new BoxLayout(down, BoxLayout.Y_AXIS));
        down.add(tabbedPane);

        add(tabbedPane);
    }

    @Override
    public void update(Object notification) {
        if(notification == null){
            tabbedPane.setTabs(new ArrayList<>());
            imeProjekta.setText("");
            autor.setText("");
            return;
        }
        if(notification instanceof Project) {
            Project p = (Project) notification;
            project = p;
            tabbedPane.setTabs(p.getChildren());
            imeProjekta.setText(p.getName());
            autor.setText(p.getAutor());
        }

        else if(notification instanceof Package) {
            Package p = (Package) notification;
            pckg = p;
            tabbedPane.setTabs(p.getChildren());
        }
        else if(notification instanceof Diagram){
            Diagram d = (Diagram) notification;
            diagram = d;
            tabbedPane.setTabs(pckg.getChildren());
        }
    }

    public void startAgregacijaState(){
        stateManager.setAgregacijaState();
        MainFrame.getInstance().getPallete().setSelectedButton(stateManager.getCurrentState());
    }
    public void startKompozicijaState(){
        stateManager.setKompozicijaState();
        MainFrame.getInstance().getPallete().setSelectedButton(stateManager.getCurrentState());
    }
    public void startZavisnostState(){
        stateManager.setZavisnostState();
        MainFrame.getInstance().getPallete().setSelectedButton(stateManager.getCurrentState());
    }
    public void startGeneralizacijaState(){
        stateManager.setGeneralizacijaState();
        MainFrame.getInstance().getPallete().setSelectedButton(stateManager.getCurrentState());
    }
    public void startKlasaState(){
        stateManager.setKlasaState();
        MainFrame.getInstance().getPallete().setSelectedButton(stateManager.getCurrentState());
    }
    public void startInterfejsState(){
        stateManager.setInterfejsState();
        MainFrame.getInstance().getPallete().setSelectedButton(stateManager.getCurrentState());
    }
    public void startEnumState(){
        stateManager.setEnumState();
        MainFrame.getInstance().getPallete().setSelectedButton(stateManager.getCurrentState());
    }
    public void startSelectionState(){
        stateManager.setSelectionState();
        MainFrame.getInstance().getPallete().setSelectedButton(stateManager.getCurrentState());
    }
    public void startMoveState(){
        stateManager.setMoveState();
        MainFrame.getInstance().getPallete().setSelectedButton(stateManager.getCurrentState());
    }
}
