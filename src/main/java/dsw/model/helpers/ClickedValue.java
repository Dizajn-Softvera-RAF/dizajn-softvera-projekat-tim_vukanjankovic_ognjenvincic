package dsw.model.helpers;

import dsw.view.painters.DevicePainter;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
@Getter
@Setter
public class ClickedValue {
    DevicePainter d;
    int index;
    int type;

    public ClickedValue(DevicePainter d, int index, int type) {
        this.d = d;
        this.index = index;
        this.type = type;
    }
}
