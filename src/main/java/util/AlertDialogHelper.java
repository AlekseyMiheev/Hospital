package util;

import com.jfoenix.controls.JFXDialog;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

/**
 * Created by Aleksey on 04.01.2017.
 */
public abstract class AlertDialogHelper {

    public void showAlert(StackPane root, JFXDialog dialog){
        dialog.show(root);
    }

}
