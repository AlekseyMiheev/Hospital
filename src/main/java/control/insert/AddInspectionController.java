package control.insert;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import entity.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import util.DBControl;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * Created by Aleksey on 07.12.2016.
 */
public class AddInspectionController implements Initializable {

    public static final String INPUT_ERROR = "Input required";

    @FXML
    StackPane root;
    @FXML
    JFXDialog dialog;
    @FXML
    JFXButton btnAccept;
    @FXML
    Label content;

    @FXML
    JFXComboBox<Doctor> doctorC;
    @FXML
    JFXComboBox<Client> clientC;
    @FXML
    JFXComboBox<Diagnosis> diagnosisC;
    @FXML
    JFXComboBox<HealingPlan> planC;
    @FXML
    JFXTextField advicesT;
    @FXML
    JFXTextField dateT;

    private Inspection inspection;
    private ObservableList<Doctor> doctors;
    private ObservableList<Client> clients;
    private ObservableList<Diagnosis> diagnosises;
    private ObservableList<HealingPlan> plans;

    private Doctor doctor;
    private Client client;
    private Diagnosis diagnosis;
    private HealingPlan plan;

    public void initialize(URL url, ResourceBundle rb) {
        doctors = FXCollections.observableArrayList(DBControl.doctors);
        clients = FXCollections.observableArrayList(DBControl.clients);
        diagnosises = FXCollections.observableArrayList(DBControl.diagnosises);
        plans = FXCollections.observableArrayList(DBControl.healingPlan);

        doctorC.setItems(doctors);
        clientC.setItems(clients);
        diagnosisC.setItems(diagnosises);
        planC.setItems(plans);
        setListeners();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate = LocalDate.now();
        dateT.setText(dtf.format(localDate));

        RequiredFieldValidator advicesValidator = new RequiredFieldValidator();
        RequiredFieldValidator dateValidator = new RequiredFieldValidator();

        advicesValidator.setMessage(INPUT_ERROR);
        dateValidator.setMessage(INPUT_ERROR);

        advicesT.getValidators().add(advicesValidator);
        dateT.getValidators().add(dateValidator);

        advicesT.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) advicesT.validate();
        });

        dateT.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) dateT.validate();
        });

    }

    private void setListeners() {
        doctorC.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Doctor>() {
            @Override
            public void changed(ObservableValue<? extends Doctor> observable, Doctor oldValue, Doctor newValue) {
                doctor = newValue;
            }
        });

        clientC.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Client>() {
            @Override
            public void changed(ObservableValue<? extends Client> observable, Client oldValue, Client newValue) {
                client = newValue;
            }
        });

        diagnosisC.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Diagnosis>() {
            @Override
            public void changed(ObservableValue<? extends Diagnosis> observable, Diagnosis oldValue, Diagnosis newValue) {
                diagnosis = newValue;
            }
        });

        planC.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<HealingPlan>() {
            @Override
            public void changed(ObservableValue<? extends HealingPlan> observable, HealingPlan oldValue, HealingPlan newValue) {
                plan = newValue;
            }
        });
    }

    public void register() {
        if (doctor != null && client != null && diagnosis != null && plan != null && advicesT.validate() && dateT.validate()) {
            inspection = new Inspection(doctor, client, diagnosis, plan, advicesT.getText(), dateT.getText());
            DBControl.addData(inspection);
            btnAccept.setStyle("-fx-background-color: #4caf50;");
            showDialog("Inspection successfully registered");
        } else {
            showDialog("Fill in all the fields");
            btnAccept.setStyle("-fx-background-color: #d50000;");
        }
    }

    private void showDialog(String message) {
        content.setText(message);
        dialog.show(root);
    }

    public void accept() {
        dialog.close();
    }
}
