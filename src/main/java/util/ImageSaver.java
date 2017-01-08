package util;

import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by Aleksey on 04.01.2017.
 */
public abstract class ImageSaver {
    private static FileChooser fileChooser = setFileChooser();

    private static FileChooser setFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        return fileChooser;
    }

    public static boolean saveToPNG(Scene scene) {
        boolean result = false;
        try {
            File file = fileChooser.showSaveDialog(new Stage());
            WritableImage snapshot = scene.snapshot(null);
            BufferedImage bufferedImage = new BufferedImage((int) scene.getWidth(), (int) scene.getHeight(), BufferedImage.TYPE_INT_ARGB);
            BufferedImage image;
            image = javafx.embed.swing.SwingFXUtils.fromFXImage(snapshot, bufferedImage);
            ImageIO.write(image, "png", file);
            result = true;
        } catch (Exception ex) {

        }
        return result;
    }


}
