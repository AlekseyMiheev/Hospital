package control.scene;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Created by Aleksey on 30.12.2016.
 */
public class DoctorController {

    private final String INSERT_LAYOUT_PATH = "/view/doctor/addDoctor.fxml";
    private final String VIEW_LAYOUT_PATH = "/view/doctor/showDoctor.fxml";

    @FXML
    Tab registerTab;

    @FXML
    Tab showTab;

    public void initialize(){
        try {
            VBox register = FXMLLoader.load(getClass().getResource(INSERT_LAYOUT_PATH));
            VBox show = FXMLLoader.load(getClass().getResource(VIEW_LAYOUT_PATH));

            registerTab.setContent(register);
            showTab.setContent(show);

        } catch (IOException ex) { }
    }

}
