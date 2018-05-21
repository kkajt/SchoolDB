package pl.kkorzycki.schooldb;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class MainWindowForOffice {

    private DBConnector dbConnector;

    private PreparedStatement statement;

    private Connection connection;

    public MainWindowForOffice() {
        dbConnector = DBConnector.getInstance();
        statement = dbConnector.getStatement();
        connection = dbConnector.getConnection();
    }

    @FXML
    private void showDataHandler(MouseEvent mouseEvent) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("SelectingData/SelectDataChooserForOffice.fxml"));
            Scene scene = new Scene(parent);
            GUI.getPrimaryStage().setScene(scene);
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }

    }

    @FXML
    private void addingHandler(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AddingChooserForOffice.fxml"));
            Scene scene = new Scene(root);
            GUI.getPrimaryStage().setScene(scene);
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }
    }

    @FXML
    private void deletingHandler(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("DeletingChooserForOffice.fxml"));
            Scene scene = new Scene(root);
            GUI.getPrimaryStage().setScene(scene);
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }
    }

    @FXML
    private void modifyingHandler(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ModifyingChooserForOffice.fxml"));
            Scene scene = new Scene(root);
            GUI.getPrimaryStage().setScene(scene);
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }
    }

}
