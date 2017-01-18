package control;

import com.jfoenix.controls.*;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import util.DBControl;
import util.ThreadHelper;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Aleksey on 23.12.2016.
 */
public class LoginController implements Initializable {

    private static final String LOGIN = "test";
    private static final String PASSWORD = "admin";
    private static final String INPUT_ERROR = "Input required";

    @FXML
    JFXTextField login;

    @FXML
    JFXPasswordField password;

    @FXML
    JFXButton logBtn;

    @FXML
    ProgressIndicator spin;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RequiredFieldValidator loginValidator = new RequiredFieldValidator();
        RequiredFieldValidator passwordValidator = new RequiredFieldValidator();
        loginValidator.setMessage(INPUT_ERROR);
        passwordValidator.setMessage(INPUT_ERROR);
        login.getValidators().add(loginValidator);
        password.getValidators().add(passwordValidator);

        login.focusedProperty().addListener((observable, oldValue, newValue) -> login.validate());
        password.focusedProperty().addListener((observable, oldValue, newValue) -> password.validate());
    }

    private Stage primaryStage;

    public void login() throws Exception {
        if (login.getText().equals(LOGIN) && password.getText().equals(PASSWORD)) {
            logBtn.setVisible(false);
            spin.setVisible(true);
            Task task = new Task() {
                @Override
                protected Object call() throws Exception {
                    DBControl.getData();
                    return null;
                }
            };
            task.setOnSucceeded(event -> openNextScene());
            ThreadHelper.startThread(task);
        }
    }

    private void openNextScene() {
        primaryStage = (Stage) login.getScene().getWindow();
        primaryStage.close();
        primaryStage = new Stage();
        try {
            VBox mainPane = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
            Scene scene = new Scene(mainPane);
            primaryStage.setTitle("Hospital");
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.setOnCloseRequest(event -> DBControl.closeConnection());
            primaryStage.show();
        } catch (Exception e) {

        }
    }

}
