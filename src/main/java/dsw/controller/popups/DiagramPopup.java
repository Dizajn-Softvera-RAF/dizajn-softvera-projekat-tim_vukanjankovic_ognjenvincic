package dsw.controller.popups;

import dsw.controller.tree.model.ClassyTreeItem;
import dsw.view.MainFrame;

import javax.swing.*;

public class DiagramPopup extends JPopupMenu implements Popup {

    public DiagramPopup(ClassyTreeItem item){
        JMenuItem opt1 = new JMenuItem("Rename");
        opt1.addActionListener(e -> MainFrame.getInstance().getActionManager().getRenameAction().popupAction(item));


        JMenuItem opt3 = new JMenuItem("Delete");
        opt3.addActionListener(e -> MainFrame.getInstance().getActionManager().getDeleteAction().popupAction(item));

        add(opt1);
        add(opt3);
    }
}
