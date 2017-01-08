/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.view.doctor;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.DBControl;
import util.Searcher;

/**
 * FXML Controller class
 *
 * @author aaa
 */
public class ViewDoctorController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    StackPane root;

    @FXML
    TableView<Doctor> doctorTable;
    @FXML
    TableColumn<Doctor, String> nameCol;
    @FXML
    TableColumn<Doctor, Speciality> specialityCol;
    @FXML
    TableColumn<Doctor, Department> departmentCol;
    @FXML
    TableColumn<Doctor, WorkingTime> timeCol;

    @FXML
    JFXTextField search_info;

    @FXML
    JFXDialog dialog;
    private ObservableList<Doctor> doctors;

    public void initialize(URL url, ResourceBundle rb) {
        root.getChildren().remove(dialog);
        setValueFactories();
        setData();
    }

    private void setValueFactories() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        specialityCol.setCellValueFactory(new PropertyValueFactory<>("speciality"));
        departmentCol.setCellValueFactory(new PropertyValueFactory<>("department"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
    }

    private void setData() {
//        DBControl.updateDoctors();
        doctors = FXCollections.observableArrayList(DBControl.doctors);
        doctorTable.setItems(doctors);
        doctorTable.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> showDetails(newValue));
    }

    private void showDetails(Doctor doctor) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/doctor/showDoctorInfo.fxml"));
            VBox root = loader.load();
            ViewDoctorInfoController controller = loader.getController();
            controller.setDoctor(doctor);
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Doctor details");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception ex) {}
    }

    public void search() {
        List<Doctor> searchResult = Searcher.searchDoctors(search_info.getText());
        doctors = FXCollections.observableArrayList(searchResult);
        doctorTable.setItems(doctors);
    }

    public void delete() {
        dialog.show(root);
    }

    public void accept(){
        Doctor toDelete = doctorTable.getSelectionModel().getSelectedItem();
        DBControl.deleteData(toDelete);
        DBControl.updateDoctors();
        setData();
        dialog.close();
    }

    public void dismiss(){
        dialog.close();
    }

}
