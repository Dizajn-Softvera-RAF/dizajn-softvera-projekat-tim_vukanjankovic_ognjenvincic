package dsw.core.logger;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class Message {
    public static int INFO = 0;
    public static int WARN = 1;
    public static int ERROR = 2;
    public static String errorColor = "\u001B[31m";
    public static String warnColor = "\u001B[33m";
    public static String infoColor = "\u001B[37m";

    String text, time, color;
    int type;

    public Message(int type, String text){
        this.type = type;
        this.text = text;
        this.time = new Timestamp(System.currentTimeMillis()).toString();
    }

    public String getType(){
        if(type == 1)return "WARN";
        if(type == 2)return "ERROR";
        return "INFO";
    }

    public String getColor(){
        if(type == 1)return Message.warnColor;
        if(type == 2)return Message.errorColor;
        return Message.infoColor;
    }


}


