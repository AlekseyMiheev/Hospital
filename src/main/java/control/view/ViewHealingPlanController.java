package control.view;

import com.jfoenix.controls.JFXTextField;
import entity.HealingPlan;
import entity.Procedure;
import entity.Speciality;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import util.DBControl;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Aleksey on 21.12.2016.
 */
public class ViewHealingPlanController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    TableView<HealingPlan> planTable;
    @FXML
    TableColumn<HealingPlan, Procedure> procedureCol;
    @FXML
    TableColumn<HealingPlan, String> descriptionCol;

    @FXML
    JFXTextField search_info;
    private ObservableList<HealingPlan> healingPlan;

    public void initialize(URL url, ResourceBundle rb) {
        procedureCol.setCellValueFactory(new PropertyValueFactory<HealingPlan, Procedure>("procedure"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<HealingPlan, String>("advices"));
        healingPlan = FXCollections.observableArrayList(DBControl.healingPlan);
        planTable.setItems(healingPlan);
    }

    public void search() {
        List<HealingPlan> searchResult = new ArrayList<HealingPlan>();
        if (search_info.getText().length() > 0) {
            for (HealingPlan c : DBControl.healingPlan) {
                if (c.getProcedure().getName().equals(search_info.getText())) {
                    searchResult.add(c);
                }
            }
            healingPlan = FXCollections.observableArrayList(searchResult);
            planTable.setItems(healingPlan);
        } else {
            healingPlan = FXCollections.observableArrayList(DBControl.healingPlan);
            planTable.setItems(healingPlan);
        }
    }

    //TODO: save to file
}
