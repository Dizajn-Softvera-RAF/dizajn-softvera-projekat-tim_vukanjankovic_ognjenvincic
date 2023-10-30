package dsw.core;

import dsw.view.MainFrame;

public class SwingGui implements Gui{
    @Override
    public void start() {
        MainFrame.getInstance().setVisible(true);
    }
}
