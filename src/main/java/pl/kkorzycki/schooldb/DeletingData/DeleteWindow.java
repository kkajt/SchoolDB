package pl.kkorzycki.schooldb.DeletingData;

import pl.kkorzycki.schooldb.DBConnector;
import pl.kkorzycki.schooldb.GUI;
import pl.kkorzycki.schooldb.LoginWindow;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;

public abstract class DeleteWindow {

    protected DBConnector dbConnector;

    protected Connection connection;

    protected PreparedStatement statement;

    protected String query;

    public DeleteWindow () {
        dbConnector = DBConnector.getInstance();
        connection = dbConnector.getConnection();
        statement = dbConnector.getStatement();
    }

    @FXML
    protected void backToStartingWindowHandler (MouseEvent mouseEvent) {
        GUI.getPrimaryStage().setScene(LoginWindow.getScene());
    }
}
