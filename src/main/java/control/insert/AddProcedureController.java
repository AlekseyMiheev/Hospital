/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.insert;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import entity.Department;
import entity.Procedure;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import util.DBControl;

import java.net.URL;
import java.util.ResourceBundle;

public class AddProcedureController implements Initializable {

    public static final String INPUT_ERROR = "Input required";
    public static final String INPUT_INTEGER_ERROR = "Integer value required";

    @FXML
    StackPane root;
    @FXML
    JFXDialog dialog;
    @FXML
    JFXButton btnAccept;
    @FXML
    Label content;

    @FXML
    JFXTextField nameF;
    @FXML
    JFXTextField descrF;
    @FXML
    JFXTextField priceF;

    private Procedure procedure;

    public void initialize(URL url, ResourceBundle rb) {
        root.getChildren().remove(dialog);
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage(INPUT_ERROR);
        nameF.getValidators().add(validator);
        nameF.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) nameF.validate();
        });

        RequiredFieldValidator validatorDescr = new RequiredFieldValidator();
        validatorDescr.setMessage(INPUT_ERROR);
        descrF.getValidators().add(validatorDescr);
        descrF.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) descrF.validate();
        });

        NumberValidator validatorPrice = new NumberValidator();
        validatorPrice.setMessage(INPUT_INTEGER_ERROR);
        priceF.getValidators().add(validatorPrice);
    }

    public void register() {
        if (descrF.validate() && nameF.validate() && priceF.validate()) {
            procedure = new Procedure(nameF.getText(), descrF.getText(), Double.parseDouble(priceF.getText()));
            DBControl.addData(procedure);
            btnAccept.setStyle("-fx-background-color: #4caf50;");
            showDialog("Procedure successfully registered");
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
