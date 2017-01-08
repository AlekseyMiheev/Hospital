package main;

import com.sun.javafx.application.LauncherImpl;
import control.SplashScreenLoader;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.DBControl;

import java.io.IOException;

/**
 * Created by Aleksey on 20.11.2016.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        VBox mainPane = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
        Scene scene = new Scene(mainPane);
        primaryStage.setTitle("Hospital");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
        DBControl.openConnection();
    }

    public static void main(String[] args) {
        LauncherImpl.launchApplication(Main.class, SplashScreenLoader.class, args);
    }

}
