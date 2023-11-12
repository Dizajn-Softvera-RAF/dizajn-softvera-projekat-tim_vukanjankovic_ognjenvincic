package dsw.controller.popups;

import dsw.controller.tree.model.ClassyTreeItem;
import dsw.view.MainFrame;

import javax.swing.*;

public class ProjectExplorerPopup extends JPopupMenu implements Popup {

    public ProjectExplorerPopup(ClassyTreeItem item){
        JMenuItem opt0 = new JMenuItem("Add project");
        opt0.addActionListener(e ->
                MainFrame.getInstance().getActionManager().getNewProjectAction().popupAction(item)
        );

        add(opt0);
    }
}

