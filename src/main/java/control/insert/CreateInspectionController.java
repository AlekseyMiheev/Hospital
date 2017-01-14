package control.insert;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
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
 * Created by Aleksey on 14.01.2017.
 */
public class CreateInspectionController implements Initializable {

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
    Label header;
    @FXML
    JFXTextField dateT;
    @FXML
    JFXTextField diagNameT;
    @FXML
    JFXTextField diagDescrT;
    @FXML
    JFXTextField procNameT;
    @FXML
    JFXTextField procDescrT;
    @FXML
    JFXTextField procPriceT;
    @FXML
    JFXTextField advicesT;

    private Inspection inspection;
    private ObservableList<Doctor> doctors;
    private ObservableList<Client> clients;

    private Doctor doctor;
    private Client client;
    private Diagnosis diagnosis;
    private Procedure procedure;
    private HealingPlan plan;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        doctors = FXCollections.observableArrayList(DBControl.doctors);
        clients = FXCollections.observableArrayList(DBControl.clients);
        header.setText(header.getText() + " " + (DBControl.inspections.size() + 1));
        doctorC.setItems(doctors);
        clientC.setItems(clients);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate = LocalDate.now();
        dateT.setText(dtf.format(localDate));

        setListeners();
        setValidators();

    }

    private void setValidators(){
        RequiredFieldValidator advicesValidator = new RequiredFieldValidator();
        RequiredFieldValidator dateValidator = new RequiredFieldValidator();
        RequiredFieldValidator dNameValidator = new RequiredFieldValidator();
        RequiredFieldValidator dDescrValidator = new RequiredFieldValidator();
        RequiredFieldValidator pNameValidator = new RequiredFieldValidator();
        RequiredFieldValidator pDescrValidator = new RequiredFieldValidator();
        NumberValidator pPriceValidator = new NumberValidator();

        advicesValidator.setMessage(INPUT_ERROR);
        dateValidator.setMessage(INPUT_ERROR);
        dNameValidator.setMessage(INPUT_ERROR);
        dDescrValidator.setMessage(INPUT_ERROR);
        pNameValidator.setMessage(INPUT_ERROR);
        pDescrValidator.setMessage(INPUT_ERROR);
        pPriceValidator.setMessage(INPUT_ERROR);

        advicesT.getValidators().add(advicesValidator);
        dateT.getValidators().add(dateValidator);
        diagNameT.getValidators().add(dNameValidator);
        diagDescrT.getValidators().add(dDescrValidator);
        procNameT.getValidators().add(pNameValidator);
        procDescrT.getValidators().add(pDescrValidator);
        procPriceT.getValidators().add(pPriceValidator);

        advicesT.focusedProperty().addListener((observable, oldValue, newValue) -> advicesT.validate());
        dateT.focusedProperty().addListener((observable, oldValue, newValue) -> dateT.validate());
        diagNameT.focusedProperty().addListener((observable, oldValue, newValue) -> diagNameT.validate());
        diagDescrT.focusedProperty().addListener((observable, oldValue, newValue) -> diagDescrT.validate());
        procNameT.focusedProperty().addListener((observable, oldValue, newValue) -> procNameT.validate());
        procDescrT.focusedProperty().addListener((observable, oldValue, newValue) -> procDescrT.validate());
        procPriceT.focusedProperty().addListener((observable, oldValue, newValue) -> procPriceT.validate());
    }

    private void setListeners() {
        doctorC.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> doctor = newValue);
        clientC.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> client = newValue);
    }

    public void register() {
        if (diagNameT.validate() && diagDescrT.validate()) {
            diagnosis = new Diagnosis(diagNameT.getText(), diagDescrT.getText());
        }
        if (procNameT.validate() && procDescrT.validate() && procPriceT.validate()) {
            procedure = new Procedure(procNameT.getText(), procDescrT.getText(), Double.parseDouble(procPriceT.getText()));
        }
        if (procedure != null && advicesT.validate()) {
            plan = new HealingPlan(procedure, advicesT.getText());
        }
        if (doctor != null && client != null && diagnosis != null && plan != null && advicesT.validate() && dateT.validate()) {
            inspection = new Inspection(doctor, client, diagnosis, plan, advicesT.getText(), dateT.getText());
            DBControl.addData(diagnosis);
            DBControl.addData(procedure);
            DBControl.addData(plan);
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
