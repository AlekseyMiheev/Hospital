/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import java.util.ResourceBundle;


/**
 * FXML Controller class
 *
 * @author aaa
 */
public class AddHealingPlanController implements Initializable {

    @FXML
    JFXTextField advicesF;

    @FXML
    JFXComboBox<Procedure> choiceProcedure;

    @FXML
    StackPane root;

    @FXML
    JFXDialog dialog;
    @FXML
    JFXButton btnAccept;
    @FXML
    Label content;

    private ObservableList<Procedure> procedures;

    private Procedure selectedProcedure;

    public void initialize(URL url, ResourceBundle rb) {
        root.getChildren().remove(dialog);
        procedures = FXCollections.observableArrayList(DBControl.procedures);

        choiceProcedure.setItems(procedures);

        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("Input required");
        advicesF.getValidators().add(validator);
        advicesF.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) advicesF.validate();
        });

        setListeners();

    }

    private void setListeners() {
        choiceProcedure.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedProcedure = newValue;
        });

    }

    public void register(){
        if (advicesF.validate() && selectedProcedure != null) {
            HealingPlan plan = new HealingPlan(selectedProcedure, advicesF.getText());
            DBControl.addData(plan);
            btnAccept.setStyle("-fx-background-color: #4caf50;");
            showDialog("Healing plan successfully registered");
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
