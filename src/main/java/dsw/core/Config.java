package dsw.core;

import dsw.core.logger.MessageGeneratorImpl;
import dsw.model.helpers.SelectionTool;
import dsw.view.painters.Shapes;

import javax.swing.*;
import javax.swing.border.Border;

public class Config {

    public static int LOG_TYPE = MessageGeneratorImpl.ALL;
    public static String LOG_FILE_PATH = "log.txt";
    public static Shapes SHAPE = Shapes.ELLIPSE;
    public static boolean SNAPPING = true;

    public static Border palleteSelectedBorder = BorderFactory.createLoweredBevelBorder();
    public static Border palleteDeselectedBorder = BorderFactory.createRaisedBevelBorder();
    public static boolean AntiAliasing = true;
    public static SelectionTool SELECTION_TOOL = SelectionTool.RECTANGLE;

    public static boolean DEBUG = false;

}
