package app;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static final int WIDTH_UNIT = 13;
    public static final int HEIGHT_UNIT = 11;
    public static final int UNIT_LENGTH = 60;


    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("CalabashMan");

        Group root = new Group();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    }
}
