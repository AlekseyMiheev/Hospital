package control.view.inspection;

import com.jfoenix.controls.JFXTextField;
import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.DBControl;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Aleksey on 20.12.2016.
 */
public class ViewInspectionTimeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    TableView<Inspection> inspectionTable;

    @FXML
    TableColumn<Inspection, Doctor> doctorCol;
    @FXML
    TableColumn<Inspection, Client> clientCol;
    @FXML
    TableColumn<Inspection, Diagnosis> diagnosisCol;
    @FXML
    TableColumn<Inspection, HealingPlan> planCol;
    @FXML
    TableColumn<Inspection, String> descrCol;
    @FXML
    TableColumn<Inspection, String> dateCol;

    @FXML
    JFXTextField start_date;
    @FXML
    JFXTextField end_date;

    private ObservableList<Inspection> inspections;

    public void initialize(URL url, ResourceBundle rb) {
        doctorCol.setCellValueFactory(new PropertyValueFactory<>("doctor"));
        clientCol.setCellValueFactory(new PropertyValueFactory<>("client"));
        diagnosisCol.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));
        planCol.setCellValueFactory(new PropertyValueFactory<>("healingPlan"));
        descrCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

        inspections = FXCollections.observableArrayList(DBControl.inspections);
        inspectionTable.setItems(inspections);

        inspectionTable.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> showDetails(newValue));
    }

    public void showDetails(Inspection inspection){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/inspection/showInspectionDetails.fxml"));
            VBox root = loader.load();
            ViewInspectionDetailsController controller = loader.getController();
            controller.setInspection(inspection);
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Inspection details");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception ex) {}
    }

    public void search() throws Exception{
        if (start_date.getText().length() > 0) {
            List<Inspection> searchResult = new ArrayList<Inspection>();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate minDate;
            LocalDate maxDate;
            LocalDate currentDate;

            minDate = LocalDate.parse(start_date.getText(), formatter);
            maxDate = LocalDate.parse(end_date.getText(), formatter);

            for (Inspection i : DBControl.inspections) {
                currentDate = LocalDate.parse(i.getDate(), formatter);
                if (currentDate.isAfter(minDate) && currentDate.isBefore(maxDate)) {
                    searchResult.add(i);
                }
            }
            inspections = FXCollections.observableArrayList(searchResult);
            inspectionTable.setItems(inspections);
        } else {
            inspections = FXCollections.observableArrayList(DBControl.inspections);
            inspectionTable.setItems(inspections);
        }
    }

}
