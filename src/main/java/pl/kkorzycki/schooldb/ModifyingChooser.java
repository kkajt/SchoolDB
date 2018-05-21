package pl.kkorzycki.schooldb;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.Objects;

public class ModifyingChooser {

    @FXML
    private void modifyStudentHandler (MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(
                    "pl/kkorzycki/schooldb/ModifyingData/ModifyStudentWindow.fxml")));
            Scene scene = new Scene(root);
            GUI.getPrimaryStage().setScene(scene);
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }
    }

    @FXML
    private void modifyWorkerHandler (MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(
                    "pl/kkorzycki/schooldb/ModifyingData/ModifyWorkerWindow.fxml")));
            Scene scene = new Scene(root);
            GUI.getPrimaryStage().setScene(scene);
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }
    }

    @FXML
    private void modifyClassHandler (MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(
                    "pl/kkorzycki/schooldb/ModifyingData/ModifyClassWindow.fxml")));
            Scene scene = new Scene(root);
            GUI.getPrimaryStage().setScene(scene);
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }
    }

    @FXML
    private void modifySubjectHandler (MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(
                    "pl/kkorzycki/schooldb/ModifyingData/ModifySubjectWindow.fxml")));
            Scene scene = new Scene(root);
            GUI.getPrimaryStage().setScene(scene);
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }
    }

    @FXML
    private void modifyUserHandler (MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(
                    "pl/kkorzycki/schooldb/ModifyingData/ModifyUserWindow.fxml")));
            Scene scene = new Scene(root);
            GUI.getPrimaryStage().setScene(scene);
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }
    }

    @FXML
    public void backToStartingWindowHandler (MouseEvent mouseEvent) {
        GUI.getPrimaryStage().setScene(LoginWindow.getScene());
    }
}
