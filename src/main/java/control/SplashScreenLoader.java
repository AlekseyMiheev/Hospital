package control;

import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.DBControl;

/**
 * Created by Aleksey on 23.12.2016.
 */
public class SplashScreenLoader extends Preloader {

    private Stage splashScreen;

    @Override
    public void start(Stage stage) throws Exception {
        splashScreen = stage;
        VBox mainPane = FXMLLoader.load(getClass().getResource("/view/splash_screen.fxml"));
        Scene scene = new Scene(mainPane);
        splashScreen.setTitle("Hospital");
        splashScreen.setScene(scene);
        splashScreen.show();
    }

    @Override
    public void handleApplicationNotification(PreloaderNotification info) { }

    @Override
    public void handleStateChangeNotification(StateChangeNotification info) {
        // Handle state change notifications.
        StateChangeNotification.Type type = info.getType();
        switch (type) {
            case BEFORE_LOAD:
                break;
            case BEFORE_INIT:
                break;
            case BEFORE_START:
                splashScreen.close();
                break;
        }
    }
}