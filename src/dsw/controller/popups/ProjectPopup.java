package dsw.controller.popups;

import dsw.controller.tree.model.ClassyTreeItem;
import dsw.view.MainFrame;

import javax.swing.*;

public class ProjectPopup extends JPopupMenu implements Popup {

    public ProjectPopup(ClassyTreeItem item){
        JMenuItem opt0 = new JMenuItem("Add mind map");
        opt0.addActionListener(e ->
                MainFrame.getInstance().getActionManager().getNewProjectAction().popupAction(item)
        );

        JMenuItem opt1 = new JMenuItem("Rename");
        opt1.addActionListener(e ->
                MainFrame.getInstance().getActionManager().getRenameAction().popupAction(item)
        );

        JMenuItem opt2 = new JMenuItem("Delete");
        opt2.addActionListener(e ->
                MainFrame.getInstance().getActionManager().getDeleteAction().popupAction(item)
        );

        add(opt0);
        add(opt1);
        add(opt2);
    }

}
