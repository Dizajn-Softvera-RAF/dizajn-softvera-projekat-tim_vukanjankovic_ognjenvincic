package dsw.core;

public class ApplicationFramework {
    protected dsw.core.SwingGui gui;

    public void run(SwingGui gui)
    {this.gui.start();}

    public void initialise(SwingGui gui){

        this.gui = gui;

    }
    private static ApplicationFramework instance;

    private ApplicationFramework(){

    }

    public static ApplicationFramework getInstance(){
        if(instance==null){
            instance = new ApplicationFramework();
        }
        return instance;
    }
}
