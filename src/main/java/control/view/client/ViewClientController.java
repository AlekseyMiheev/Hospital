package control.view.client;

import com.jfoenix.controls.JFXTextField;
import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.DBControl;
import util.Searcher;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Aleksey on 20.12.2016.
 */
public class ViewClientController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    TableView<Client> clientTable;
    @FXML
    TableColumn<Client, String> nameCol;
    @FXML
    TableColumn<Client, String> birthdayCol;
    @FXML
    TableColumn<Client, String> passportCol;
    @FXML
    TableColumn<Client, String> addressCol;

    @FXML
    JFXTextField search_info;
    private ObservableList<Client> clients;

    public void initialize(URL url, ResourceBundle rb) {
//        DBControl.updateClients();
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        birthdayCol.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        passportCol.setCellValueFactory(new PropertyValueFactory<>("passportData"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        clients = FXCollections.observableArrayList(DBControl.clients);
        clientTable.setItems(clients);
        clientTable.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> showDetails(newValue));
    }

    private void showDetails(Client client) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/client/showClientInfo.fxml"));
            VBox root = loader.load();
            ViewClientInfoController controller = loader.getController();
            controller.setClient(client);
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Client details");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception ex) {}
    }

    public void search() {
        List<Client> searchResult = Searcher.searchClients(search_info.getText());
        clients = FXCollections.observableArrayList(searchResult);
        clientTable.setItems(clients);
    }

}
