package pl.kkorzycki.schooldb.InsertingData;

import pl.kkorzycki.schooldb.DBConnector;
import pl.kkorzycki.schooldb.GUI;
import pl.kkorzycki.schooldb.LoginWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertUserWindow {

    private DBConnector dbConnector;

    private Connection connection;

    private PreparedStatement statement;

    private String query;

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    private TextField code;

    @FXML
    private TextField privileges;

    @FXML
    private Label label;

    public InsertUserWindow () {
        dbConnector = DBConnector.getInstance();
        connection = dbConnector.getConnection();
        statement = dbConnector.getStatement();
    }

    @FXML
    private void addUserToDatabaseHandler (MouseEvent mouseEvent) {
        query = "CALL createUser ('" + login.getText() + "','" +
                password.getText() + "','" + code.getText()  + "','" +
                privileges.getText() + "')";
        try {
            label.setText("");
            statement = connection.prepareStatement(query);
            statement.executeQuery();
            clearTextFields();
        } catch (SQLException ex) {
            label.setText("Nie można dodać użytkownika!");
        }
    }

    @FXML
    public void backToStartingWindowHandler (ActionEvent actionEvent) {
        GUI.getPrimaryStage().setScene(LoginWindow.getScene());
    }

    private void clearTextFields () {
        login.clear();
        password.clear();
        code.clear();
        privileges.clear();
    }
}
