package control.view.diagnosis;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
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
import javafx.scene.layout.VBox;
import util.DBControl;
import util.ImageSaver;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Aleksey on 22.12.2016.
 */
public class ViewDiagnosisInfoController implements Initializable {

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
    Label description;

    @FXML
    TableView<Inspection> inspectionTable;
    @FXML
    TableColumn<Inspection, Client> clientCol;
    @FXML
    TableColumn<Inspection, Doctor> doctorCol;
    @FXML
    TableColumn<Inspection, String> dateCol;

    private ObservableList<Inspection> inspections;

    public void initialize(URL url, ResourceBundle rb) {
        root.getChildren().remove(dialog);
        doctorCol.setCellValueFactory(new PropertyValueFactory<>("doctor"));
        clientCol.setCellValueFactory(new PropertyValueFactory<>("client"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        inspections = FXCollections.observableList(DBControl.getInspectionsByDiagnosis(diagnosis));
        inspectionTable.setItems(inspections);
        header.setText(header.getText() + " " + diagnosis.getName());
        description.setText(diagnosis.getDescription());
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