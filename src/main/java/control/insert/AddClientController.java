/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.insert;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import entity.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import util.DBControl;

public class AddClientController implements Initializable {

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
    JFXTextField nameF;
    @FXML
    JFXTextField dateF;
    @FXML
    JFXTextField passportF;
    @FXML
    JFXTextField addressF;

    private Client client;

    public void initialize(URL url, ResourceBundle rb) {
        root.getChildren().remove(dialog);
        RequiredFieldValidator nameValidator = new RequiredFieldValidator();
        RequiredFieldValidator dateValidator = new RequiredFieldValidator();
        RequiredFieldValidator passportValidator = new RequiredFieldValidator();
        RequiredFieldValidator addressValidator = new RequiredFieldValidator();
        nameValidator.setMessage(INPUT_ERROR);
        dateValidator.setMessage(INPUT_ERROR);
        passportValidator.setMessage(INPUT_ERROR);
        addressValidator.setMessage(INPUT_ERROR);

        nameF.getValidators().add(nameValidator);
        dateF.getValidators().add(dateValidator);
        passportF.getValidators().add(passportValidator);
        addressF.getValidators().add(addressValidator);

        nameF.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) nameF.validate();
        });
        dateF.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) dateF.validate();
        });
        passportF.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) passportF.validate();
        });
        addressF.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) addressF.validate();
        });
    }

    public void register() {
        if (nameF.validate() && dateF.validate() && passportF.validate() && addressF.validate()) {
            client = new Client(nameF.getText(), dateF.getText(), passportF.getText(), addressF.getText());
            DBControl.addData(client);
            btnAccept.setStyle("-fx-background-color: #4caf50;");
            showDialog("Client successfully registered");
        } else {
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
