package control.view;

import com.jfoenix.controls.JFXTextField;
import entity.Procedure;
import entity.Speciality;
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
public class ViewProcedureController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    TableView<Procedure> procedureTable;
    @FXML
    TableColumn<Procedure, String> nameCol;
    @FXML
    TableColumn<Procedure, String> descriptionCol;
    @FXML
    TableColumn<Procedure, Double> priceCol;

    @FXML
    JFXTextField search_info;
    private ObservableList<Procedure> procedures;

    public void initialize(URL url, ResourceBundle rb) {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        procedures = FXCollections.observableArrayList(DBControl.procedures);
        procedureTable.setItems(procedures);
    }

    public void search() {
        List<Procedure> searchResult = Searcher.searchProcedures(search_info.getText());
            procedures = FXCollections.observableArrayList(searchResult);
            procedureTable.setItems(procedures);
    }
}
