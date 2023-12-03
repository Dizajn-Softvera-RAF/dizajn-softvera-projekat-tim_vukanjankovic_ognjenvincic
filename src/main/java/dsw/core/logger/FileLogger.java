package dsw.core.logger;

import dsw.observer.ISubscriber;
import dsw.core.ApplicationFramework;
import dsw.core.Config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileLogger implements Logger, ISubscriber {

    File f;

    public FileLogger() {
        try {
            f = new File(Config.LOG_FILE_PATH);
            if (f.createNewFile()) {
                Files.write(Paths.get(Config.LOG_FILE_PATH), "".getBytes(), StandardOpenOption.WRITE);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public void log(Message msg) {
        if (ApplicationFramework.getInstance().getMessageGenerator().getType() == MessageGeneratorImpl.CONSOLE || ApplicationFramework.getInstance().getMessageGenerator().getType() == MessageGeneratorImpl.NONE) return;
        try {
            Files.write(Paths.get(Config.LOG_FILE_PATH), String.format("[%s] [%s] [%s]\n", msg.getType(), msg.getTime(), msg.getText()).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void update(Object notification) {
        Message msg = (Message) notification;
        log(msg);
    }

}
