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
import util.Searcher;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ViewInspectionController implements Initializable {

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
    JFXTextField search_info;

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

    private void showDetails(Inspection inspection) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/inspection/showInspectionDetails.fxml"));
            VBox root = loader.load();
            ViewInspectionDetailsController controller = loader.getController();
            controller.setInspection(inspection);
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Inspection details");
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.show();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    public void search() {
        List<Inspection> searchResult = Searcher.searchInspections(search_info.getText());
        inspections = FXCollections.observableArrayList(searchResult);
        inspectionTable.setItems(inspections);
    }
}
