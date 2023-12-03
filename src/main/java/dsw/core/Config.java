package dsw.core;

import dsw.core.logger.MessageGeneratorImpl;
import dsw.view.painters.Shapes;

import javax.swing.*;
import javax.swing.border.Border;

public class Config {

    public static int LOG_TYPE = MessageGeneratorImpl.ALL;
    public static String LOG_FILE_PATH = "log.txt";
    public static Shapes SHAPE = Shapes.ELLIPSE;
    public static Border palleteSelectedBorder = BorderFactory.createLoweredBevelBorder();
    public static Border palleteDeselectedBorder = BorderFactory.createRaisedBevelBorder();



}
