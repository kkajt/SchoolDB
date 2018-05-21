package pl.kkorzycki.schooldb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUI extends Application {

    private static Stage primaryStage;

    private static Scene scene;

    private Parent root;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GUI.primaryStage = primaryStage;
        root =  FXMLLoader.load(getClass().getResource("LoginWindow.fxml"));
        scene = new Scene(root, 300, 300);
        primaryStage.setTitle("Związek Nauczycielstwa Polskiego");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static Scene getScene() {
        return scene;
    }
}
