package control.view.diagnosis;

import com.jfoenix.controls.JFXTextField;
import control.view.speciality.ViewSpecialityInfoController;
import entity.Diagnosis;
import entity.Speciality;
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

/**
 * Created by Aleksey on 21.12.2016.
 */
public class ViewDiagnosisController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    TableView<Diagnosis> diagnosisTable;
    @FXML
    TableColumn<Diagnosis, String> nameCol;
    @FXML
    TableColumn<Diagnosis, String> descriptionCol;

    @FXML
    JFXTextField search_info;
    private ObservableList<Diagnosis> diagnoses;

    public void initialize(URL url, ResourceBundle rb) {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        diagnoses = FXCollections.observableArrayList(DBControl.diagnosises);
        diagnosisTable.setItems(diagnoses);

        diagnosisTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showDetails(newValue));
    }

    private void showDetails(Diagnosis diagnosis) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/diagnosis/showDiagnosisInfo.fxml"));
            VBox root = loader.load();
            ViewDiagnosisInfoController controller = loader.getController();
            controller.setDiagnosis(diagnosis);
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Diagnosis details");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception ex) {}
    }

    public void search() {
        List<Diagnosis> searchResult = Searcher.searchDiagnoses(search_info.getText());
        diagnoses = FXCollections.observableArrayList(searchResult);
        diagnosisTable.setItems(diagnoses);
    }
}

