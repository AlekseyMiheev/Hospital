package control.view.department;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import entity.Department;
import entity.Doctor;
import entity.Speciality;
import entity.WorkingTime;
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
import java.util.ResourceBundle;

/**
 * Created by Aleksey on 22.12.2016.
 */
public class ViewDepartmentInfoController implements Initializable {

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
    TableView<Doctor> doctorTable;
    @FXML
    TableColumn<Doctor, String> doctorCol;
    @FXML
    TableColumn<Doctor, Department> specialityCol;
    @FXML
    TableColumn<Doctor, WorkingTime> timeCol;

    private ObservableList<Doctor> doctors;

    public void initialize(URL url, ResourceBundle rb) {
        root.getChildren().remove(dialog);
        doctorCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        specialityCol.setCellValueFactory(new PropertyValueFactory<>("speciality"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
    }

    public void setDepartment(Department department) {
        doctors = FXCollections.observableList(DBControl.getDoctorsByDepartment(department));
        doctorTable.setItems(doctors);
        header.setText(header.getText() + " " + department.getName());
        description.setText(department.getDescription());
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