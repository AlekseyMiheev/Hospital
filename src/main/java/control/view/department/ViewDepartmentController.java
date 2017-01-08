package control.view.department;

import com.jfoenix.controls.JFXTextField;
import entity.Department;
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
public class ViewDepartmentController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    TableView<Department> depTable;
    @FXML
    TableColumn<Department, String> departmentCol;
    @FXML
    TableColumn<Department, String> descriptionCol;

    @FXML
    JFXTextField search_info;
    private ObservableList<Department> specialities;

    public void initialize(URL url, ResourceBundle rb) {
        departmentCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        specialities = FXCollections.observableArrayList(DBControl.departments);
        depTable.setItems(specialities);
        depTable.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> showDetails(newValue));
    }

    private void showDetails(Department department) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/department/showDepartmentInfo.fxml"));
            VBox root = loader.load();
            ViewDepartmentInfoController controller = loader.getController();
            controller.setDepartment(department);
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Department details");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception ex) {
        }
    }

    public void search() {
        List<Department> searchResult = Searcher.searchDepartments(search_info.getText());
        specialities = FXCollections.observableArrayList(searchResult);
        depTable.setItems(specialities);
    }
}
