package dsw.core;

import dsw.core.logger.ConsoleLogger;
import dsw.core.logger.FileLogger;
import dsw.core.logger.Logger;
import dsw.core.logger.MessageGeneratorImpl;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationFramework {
    protected Gui gui;
    protected ClassyRepository classyRepository;
    protected MessageGeneratorImpl messageGenerator;
    protected Logger consoleLogger, fileLogger;


    public void run(Gui gui) {
        this.gui.start();
    }

    public void initialise(Gui gui, ClassyRepository classyRepository, MessageGeneratorImpl mg, ConsoleLogger cl, FileLogger fl){
        this.gui = gui;
        this.classyRepository = classyRepository;
        this.messageGenerator = mg;
        this.consoleLogger = cl;
        this.fileLogger = fl;
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
