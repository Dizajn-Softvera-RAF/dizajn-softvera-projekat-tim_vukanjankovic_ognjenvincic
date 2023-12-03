package dsw.model.helpers;

import java.awt.*;

public class CustomCursor {
    private Cursor bucketCursor;

    public CustomCursor() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage(getClass().getResource("/images/add.png"));
        Point hotSpot = new Point(9,26);
        bucketCursor = toolkit.createCustomCursor(image, hotSpot, "Brush");
    }
}
