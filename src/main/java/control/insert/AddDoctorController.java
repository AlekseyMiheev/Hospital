/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.insert;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.*;
import com.jfoenix.controls.JFXPopup.*;
import com.jfoenix.validation.RequiredFieldValidator;
import entity.Department;
import entity.Doctor;
import entity.Speciality;
import entity.WorkingTime;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import util.DBControl;


/**
 * FXML Controller class
 *
 * @author aaa
 */
public class AddDoctorController implements Initializable {

    @FXML
    JFXTextField doctorName;

    @FXML
    JFXComboBox<WorkingTime> choiceTime;

    @FXML
    JFXComboBox<Speciality> choiceSpeciality;

    @FXML
    JFXComboBox<Department> choiceDepartment;

    @FXML
    StackPane root;

    @FXML
    JFXDialog dialog;
    @FXML
    JFXButton btnAccept;
    @FXML
    Label content;

    private ObservableList<WorkingTime> time;
    private ObservableList<Speciality> specialities;
    private ObservableList<Department> departments;

    private WorkingTime doctorsTime;
    private Speciality doctorsSpeciality;
    private Department doctorsDepartment;


    public void initialize(URL url, ResourceBundle rb) {
        root.getChildren().remove(dialog);
        time = FXCollections.observableArrayList(DBControl.time);
        specialities = FXCollections.observableArrayList(DBControl.specialities);
        departments = FXCollections.observableArrayList(DBControl.departments);

        choiceTime.setItems(time);
        choiceSpeciality.setItems(specialities);
        choiceDepartment.setItems(departments);

        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("Input required");
        doctorName.getValidators().add(validator);
        doctorName.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) doctorName.validate();
        });

        setListeners();

    }

    private void setListeners() {
        choiceTime.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<WorkingTime>() {
            public void changed(ObservableValue<? extends WorkingTime> observable, WorkingTime oldValue, WorkingTime newValue) {
                doctorsTime = newValue;
            }
        });

        choiceSpeciality.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Speciality>() {
            public void changed(ObservableValue<? extends Speciality> observable, Speciality oldValue, Speciality newValue) {
                doctorsSpeciality = newValue;
            }
        });

        choiceDepartment.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Department>() {
            public void changed(ObservableValue<? extends Department> observable, Department oldValue, Department newValue) {
                doctorsDepartment = newValue;
            }
        });
    }

    public void registerDoctor(){
        if (doctorName.getText().length() > 0 && doctorsSpeciality != null && doctorsDepartment != null && doctorsTime != null) {
            Doctor doctor = new Doctor(doctorName.getText(), doctorsSpeciality, doctorsDepartment, doctorsTime);
            DBControl.addData(doctor);
            btnAccept.setStyle("-fx-background-color: #4caf50;");
            showDialog("Doctor successfully registered");
        }
        else {
            showDialog("Fill in all the fields");
            btnAccept.setStyle("-fx-background-color: #d50000;");
        }
    }

    private void showDialog(String message) {
        content.setText(message);
        dialog.show(root);
    }

    public void accept(){
        dialog.close();
    }

}
