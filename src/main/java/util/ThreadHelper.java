package util;

import javafx.concurrent.Task;

/**
 * Created by Aleksey on 27.12.2016.
 */
public abstract class ThreadHelper {

    public static void startThread(Task task){
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }

}
