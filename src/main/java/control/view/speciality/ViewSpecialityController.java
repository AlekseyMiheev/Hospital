package control.view.speciality;

import com.jfoenix.controls.JFXTextField;
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
public class ViewSpecialityController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    TableView<Speciality> specTable;
    @FXML
    TableColumn<Speciality, String> specialityCol;
    @FXML
    TableColumn<Speciality, String> descriptionCol;

    @FXML
    JFXTextField search_info;
    private ObservableList<Speciality> specialities;

    public void initialize(URL url, ResourceBundle rb) {
        specialityCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        specialities = FXCollections.observableArrayList(DBControl.specialities);
        specTable.setItems(specialities);
        specTable.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> showDetails(newValue));
    }

    private void showDetails(Speciality speciality) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/speciality/showSpecialityInfo.fxml"));
            VBox root = loader.load();
            ViewSpecialityInfoController controller = loader.getController();
            controller.setSpeciality(speciality);
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Speciality details");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception ex) {}
    }

    public void search() {
        List<Speciality> searchResult = Searcher.searchSpecialities(search_info.getText());
            specialities = FXCollections.observableArrayList(searchResult);
            specTable.setItems(specialities);
    }
}
