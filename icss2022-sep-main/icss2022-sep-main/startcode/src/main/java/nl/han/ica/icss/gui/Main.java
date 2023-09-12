package nl.han.ica.icss.gui;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) throws IOException {
        MainGui.launch(MainGui.class,args);

        Handler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.INFO);

        Logger logger = Logger.getLogger("");
        logger.addHandler(consoleHandler);
        logger.setLevel(Level.INFO);
    }
}
