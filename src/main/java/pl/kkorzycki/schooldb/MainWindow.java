package pl.kkorzycki.schooldb;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class MainWindow {

    private DBConnector dbConnector;

    private PreparedStatement statement;

    private Connection connection;

    public MainWindow() {
        dbConnector = DBConnector.getInstance();
        statement = dbConnector.getStatement();
        connection = dbConnector.getConnection();
    }

    @FXML
    private void showDataHandler(MouseEvent mouseEvent) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("SelectingData/SelectDataChooser.fxml"));
            Scene scene = new Scene(parent);
            GUI.getPrimaryStage().setScene(scene);
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }

    }

    @FXML
    private void addingHandler(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AddingChooser.fxml"));
            Scene scene = new Scene(root);
            GUI.getPrimaryStage().setScene(scene);
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }
    }

    @FXML
    private void deletingHandler(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("DeletingChooser.fxml"));
            Scene scene = new Scene(root);
            GUI.getPrimaryStage().setScene(scene);
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }
    }

    @FXML
    private void modifyingHandler(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ModifyingChooser.fxml"));
            Scene scene = new Scene(root);
            GUI.getPrimaryStage().setScene(scene);
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }
    }

    @FXML
    private void makeBackupCopyHandler(MouseEvent mouseEvent) {

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(
                    "pl/kkorzycki/schooldb/BackupWindow.fxml")));
            Scene scene = new Scene(root);
            GUI.getPrimaryStage().setScene(scene);
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }
    }

    @FXML
    private void newSchoolYearHandler(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(
                    "pl/kkorzycki/schooldb/BackupWindow.fxml")));
            Scene scene = new Scene(root);
            GUI.getPrimaryStage().setScene(scene);
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }
        try {
            statement = connection.prepareStatement("CALL test()");
            statement.executeQuery();
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }

    @FXML
    private void restoreBackupCopyHandler(MouseEvent mouseEvent) {

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(
                    "pl/kkorzycki/schooldb/RestoreWindow.fxml")));
            Scene scene = new Scene(root);
            GUI.getPrimaryStage().setScene(scene);
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }
    }
}
