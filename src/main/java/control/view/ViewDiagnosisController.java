package control.view;

import com.jfoenix.controls.JFXTextField;
import entity.Diagnosis;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import util.DBControl;
import util.Searcher;

import java.net.URL;
import java.util.ArrayList;
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
    private ObservableList<Diagnosis> diagnosises;

    public void initialize(URL url, ResourceBundle rb) {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        diagnosises = FXCollections.observableArrayList(DBControl.diagnosises);
        diagnosisTable.setItems(diagnosises);
    }

    public void search() {
        List<Diagnosis> searchResult = Searcher.searchDiagnoses(search_info.getText());
        diagnosises = FXCollections.observableArrayList(searchResult);
        diagnosisTable.setItems(diagnosises);
    }
}

