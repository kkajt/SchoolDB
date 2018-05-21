package pl.kkorzycki.schooldb;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.Objects;

public class DeletingChooserForOffice {

    @FXML
    public void deleteStudentHandler (MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(
                    "pl/kkorzycki/schooldb/DeletingData/DeleteStudentWindow.fxml")));
            Scene scene = new Scene(root);
            GUI.getPrimaryStage().setScene(scene);
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }
    }

    @FXML
    public void deleteWorkerHandler (MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(
                    "pl/kkorzycki/schooldb/DeletingData/DeleteWorkerWindow.fxml")));
            Scene scene = new Scene(root);
            GUI.getPrimaryStage().setScene(scene);
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }
    }

    @FXML
    public void deleteSubjectHandler (MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(
                    "pl/kkorzycki/schooldb/DeletingData/DeleteSubjectWindow.fxml")));
            Scene scene = new Scene(root);
            GUI.getPrimaryStage().setScene(scene);
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }
    }

    @FXML
    public void deleteClassHandler (MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(
                    "pl/kkorzycki/schooldb/DeletingData/DeleteClassWindow.fxml")));
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
