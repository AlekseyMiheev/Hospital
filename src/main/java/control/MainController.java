package control;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import util.DBControl;
import util.ThreadHelper;
import util.XLSHelper;

/**
 * Created by Aleksey on 07.12.2016.
 */
public class MainController {

    @FXML
    JFXButton loadDataBtn;

    @FXML
    VBox dataPane;

    @FXML
    JFXListView<Label> menu;

    private Task update;
    private Stage primaryStage;

    private void setDataPane(Node node) {
        dataPane.getChildren().setAll(node);
    }

    private VBox fadeAnimate(String url) {
        VBox v = null;
        try {
            v = FXMLLoader.load(getClass().getResource(url));
            FadeTransition ft = new FadeTransition(Duration.millis(500));
            ft.setNode(v);
            ft.setFromValue(0.1);
            ft.setToValue(1);
            ft.setCycleCount(1);
            ft.setAutoReverse(false);
            ft.play();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
        return v;
    }

    public void saveToExcel() {
        XLSHelper.saveInfo();
    }

    public void logout(){
        primaryStage = (Stage) dataPane.getScene().getWindow();
        primaryStage.close();
        primaryStage = new Stage();
        try {
            VBox mainPane = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
            Scene scene = new Scene(mainPane);
            primaryStage.setTitle("Hospital");
            primaryStage.setScene(scene);
            primaryStage.setMaximized(false);
            primaryStage.setOnCloseRequest(event -> DBControl.closeConnection());
            primaryStage.show();
        } catch (Exception e) {

        }
    }

    public void loadPane() throws IOException {
        Node current = dataPane.getChildren().get(0);
        update = new Task() {
            @Override
            protected Object call() throws Exception {
                DBControl.getData();
                return null;
            }
        };
        update.setOnSucceeded(event1 -> setDataPane(current));
        startThread(update);
    }

    private void setLoader() throws IOException {
        VBox v = FXMLLoader.load(getClass().getResource("/view/loader.fxml"));
        dataPane.getChildren().setAll(v);
    }

    private void startThread(Task task) {
        try {
            setLoader();
        } catch (IOException ex) {
        }
        ThreadHelper.startThread(task);
    }

    public void showDoctorsLayout() throws IOException {
        update = new Task() {
            @Override
            protected Object call() throws Exception {
                DBControl.updateDoctors();
                return null;
            }
        };
        update.setOnSucceeded(event -> setDataPane(fadeAnimate("/view/doctor/doctors.fxml")));
        startThread(update);
    }

    public void showClientsLayout() throws IOException {
        update = new Task() {
            @Override
            protected Object call() throws Exception {
                DBControl.updateClients();
                return null;
            }
        };
        update.setOnSucceeded(event -> setDataPane(fadeAnimate("/view/client/clients.fxml")));
        startThread(update);
    }

    public void showInspectionsLayout() throws IOException {
        update = new Task() {
            @Override
            protected Object call() throws Exception {
                DBControl.updateInspections();
                return null;
            }
        };
        update.setOnSucceeded(event -> setDataPane(fadeAnimate("/view/inspection/inspections.fxml")));
        startThread(update);
    }

    public void showSpecialitiesLayout() throws IOException {
        update = new Task() {
            @Override
            protected Object call() throws Exception {
                DBControl.updateSpecialities();
                return null;
            }
        };
        update.setOnSucceeded(event -> setDataPane(fadeAnimate("/view/speciality/specialities.fxml")));
        startThread(update);
    }

    public void showDepartmentsLayout() throws IOException {
        update = new Task() {
            @Override
            protected Object call() throws Exception {
                DBControl.updateDepartments();
                return null;
            }
        };
        update.setOnSucceeded(event -> setDataPane(fadeAnimate("/view/department/departments.fxml")));
        startThread(update);
    }

    public void showProceduresLayout() throws IOException {
        update = new Task() {
            @Override
            protected Object call() throws Exception {
                DBControl.updateProcedures();
                return null;
            }
        };
        update.setOnSucceeded(event -> setDataPane(fadeAnimate("/view/procedure/procedures.fxml")));
        startThread(update);
    }

    public void showDiagnosesLayout() throws IOException {
        update = new Task() {
            @Override
            protected Object call() throws Exception {
                DBControl.updateDiagnoses();
                return null;
            }
        };
        update.setOnSucceeded(event -> setDataPane(fadeAnimate("/view/diagnosis/diagnoses.fxml")));
        startThread(update);
    }

    public void showPlansLayout() throws IOException {
        update = new Task() {
            @Override
            protected Object call() throws Exception {
                DBControl.updatePlans();
                return null;
            }
        };
        update.setOnSucceeded(event -> setDataPane(fadeAnimate("/view/healing_plan/plans.fxml")));
        startThread(update);
    }
}
