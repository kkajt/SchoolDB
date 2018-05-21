package pl.kkorzycki.schooldb;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.Objects;

public class AddingChooser {

    @FXML
    public void addStudentHandler (MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(
                    "pl/kkorzycki/schooldb/InsertingData/InsertStudentWindow.fxml")));

            Scene scene = new Scene(root);
            GUI.getPrimaryStage().setScene(scene);
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }
    }

    @FXML
    public void addWorkerHandler (MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(
                    "pl/kkorzycki/schooldb/InsertingData/InsertWorkerWindow.fxml")));
            Scene scene = new Scene(root);
            GUI.getPrimaryStage().setScene(scene);
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }
    }

    @FXML
    public void addClassHandler (MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(
                    "pl/kkorzycki/schooldb/InsertingData/InsertClassWindow.fxml")));
            Scene scene = new Scene(root);
            GUI.getPrimaryStage().setScene(scene);
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }
    }

    @FXML
    public void addSubjectHandler (MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(
                    "pl/kkorzycki/schooldb/InsertingData/InsertSubjectWindow.fxml")));
            Scene scene = new Scene(root);
            GUI.getPrimaryStage().setScene(scene);
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }
    }

    @FXML
    public void addUserHandler (MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(
                    "pl/kkorzycki/schooldb/InsertingData/InsertUserWindow.fxml")));
            Scene scene = new Scene(root);
            GUI.getPrimaryStage().setScene(scene);
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }
    }

    @FXML
    public void backToStartingWindowHandler (ActionEvent actionEvent) {
        GUI.getPrimaryStage().setScene(LoginWindow.getScene());
    }
}
