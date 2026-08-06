package itcor.pomeedoro;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Simple logger
 */
public class Log {
    private static final DateTimeFormatter TIME = DateTimeFormatter.ofPattern("HH:mm:ss");

    private Log() {} // no constructor

    private static String timestamp() {
        return LocalDateTime.now().format(TIME);
    }

    public static void info(String message) {
        System.out.println("[" + timestamp() + "][INFO]: " + message);
    }


    public static void warn(String message) {
        System.out.println("[" + timestamp() + "][WARN]: " + message);
    }
}
