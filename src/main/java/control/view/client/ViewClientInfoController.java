package control.view.client;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import util.DBControl;
import util.ImageSaver;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Aleksey on 22.12.2016.
 */
public class ViewClientInfoController implements Initializable {

    @FXML
    StackPane root;
    @FXML
    JFXDialog dialog;
    @FXML
    Label dialogContainer;
    @FXML
    JFXButton btnAccept;

    @FXML
    Label header;
    @FXML
    Label name;
    @FXML
    Label date;
    @FXML
    Label passport;
    @FXML
    Label address;

    @FXML
    TableView<Inspection> inspectionTable;
    @FXML
    TableColumn<Inspection, Doctor> doctorCol;
    @FXML
    TableColumn<Inspection, Diagnosis> diagnosisCol;
    @FXML
    TableColumn<Inspection, HealingPlan> planCol;
    @FXML
    TableColumn<Inspection, String> descrCol;
    @FXML
    TableColumn<Inspection, String> dateCol;

    private ObservableList<Inspection> inspections;

    public void initialize(URL url, ResourceBundle rb) {
        root.getChildren().remove(dialog);
        doctorCol.setCellValueFactory(new PropertyValueFactory<>("doctor"));
        diagnosisCol.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));
        planCol.setCellValueFactory(new PropertyValueFactory<>("healingPlan"));
        descrCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    public void setClient(Client client) {
        inspections = FXCollections.observableList(DBControl.getInspectionsByPerson(client));
        inspectionTable.setItems(inspections);
        header.setText(header.getText() + " " + client.getId());
        name.setText(client.getName());
        date.setText(client.getBirthday());
        passport.setText(client.getPassportData());
        address.setText(client.getAddress());
    }

    public void saveToImage() {
        boolean savingResult;
        savingResult = ImageSaver.saveToPNG(header.getScene());

        if (savingResult) {
            dialogContainer.setText("File saved successfully");
            btnAccept.setStyle("-fx-background-color: #4caf50;");
        }
        dialog.show(root);
    }

    public void accept(){
        dialog.close();
    }

}