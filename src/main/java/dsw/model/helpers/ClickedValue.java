package dsw.model.helpers;

import dsw.view.painters.ConnectionPainter;
import dsw.view.painters.ElementPainter;
import dsw.view.painters.InterclassPainter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClickedValue {
    ElementPainter d;
    int index;
    int type;

    public ClickedValue(ElementPainter d, int index, int type) {
        this.d = d;
        this.index = index;
        this.type = type;
    }
}
