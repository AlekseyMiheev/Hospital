package control.view.inspection;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import entity.Inspection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import util.ImageSaver;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Aleksey on 04.01.2017.
 */
public class ViewInspectionDetailsController implements Initializable {

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
    Label headerDate;

    @FXML
    Label doctorName;
    @FXML
    Label doctorSpeciality;
    @FXML
    Label doctorDepartment;

    @FXML
    Label clientName;
    @FXML
    Label clientDate;
    @FXML
    Label clientPassport;
    @FXML
    Label clientAddress;

    @FXML
    Label diagnosisName;
    @FXML
    Label diagnosisDescription;

    @FXML
    Label procedureName;
    @FXML
    Label procedureDescription;
    @FXML
    Label procedurePrice;

    @FXML
    Label advices;

    private Inspection inspection;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        root.getChildren().remove(dialog);
    }

    public void setInspection(Inspection inspection) {
        this.inspection = inspection;
        setData();
    }

    private void setData() {
        header.setText(header.getText() + " " + inspection.getId());
        headerDate.setText(inspection.getDate());

        doctorName.setText(inspection.getDoctor().getName());
        doctorSpeciality.setText(inspection.getDoctor().getSpeciality().getName());
        doctorDepartment.setText(inspection.getDoctor().getDepartment().getName());

        clientName.setText(inspection.getClient().getName());
        clientDate.setText(inspection.getClient().getBirthday());
        clientPassport.setText(inspection.getClient().getPassportData());
        clientAddress.setText(inspection.getClient().getAddress());

        diagnosisName.setText(inspection.getDiagnosis().getName());
        diagnosisDescription.setText(inspection.getDiagnosis().getDescription());

        procedureName.setText(inspection.getHealingPlan().getProcedure().getName());
        procedureDescription.setText(inspection.getHealingPlan().getProcedure().getDescription());
        procedurePrice.setText(String.valueOf(inspection.getHealingPlan().getProcedure().getPrice()));

        advices.setText(inspection.getHealingPlan().getAdvices());
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

    public void accept() {
        dialog.close();
    }

}
