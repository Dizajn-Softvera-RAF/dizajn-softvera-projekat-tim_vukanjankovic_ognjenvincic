package dsw.controller.popups;

import dsw.controller.tree.model.ClassyTreeItem;

public class PopupFactory {

    public Popup getPopup(PopupType type, ClassyTreeItem item) {
        if (type == PopupType.PROJECT_EXPLORER) {
            return new ProjectExplorerPopup(item);
        }
        if (type == PopupType.PROJECT) {
            return new ProjectPopup(item);
        }
        if (type == PopupType.DIAGRAM) {
            return new DiagramPopup(item);
        }
        return null;
    }

}
