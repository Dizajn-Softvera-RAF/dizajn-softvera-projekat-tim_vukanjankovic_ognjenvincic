package dsw;

import dsw.core.ApplicationFramework;
import dsw.core.SwingGui;

public class AppCore {
    public static void main(String[] args) {
        ApplicationFramework appCore = ApplicationFramework.getInstance();
        SwingGui gui = new SwingGui();
        appCore.initialise(gui);
        appCore.run(gui);
    }
}
