package dsw;

import dsw.core.*;
import dsw.core.logger.ConsoleLogger;
import dsw.core.logger.FileLogger;
import dsw.core.logger.MessageGeneratorImpl;
import dsw.repository.ClassyRepositoryImpl;

public class AppCore {
    public static void main(String[] args) {
        ApplicationFramework appCore = ApplicationFramework.getInstance();
        SwingGui gui = new SwingGui();
        ClassyRepository classyRepository = new ClassyRepositoryImpl();
        ConsoleLogger cl = new ConsoleLogger();
        FileLogger fl = new FileLogger();
        MessageGeneratorImpl mg = new MessageGeneratorImpl(Config.LOG_TYPE);
        mg.addSubscriber(fl);
        mg.addSubscriber(cl);
        mg.addSubscriber(gui);

        appCore.initialise(gui, classyRepository, mg, cl, fl);
        appCore.run(gui);
    }
}