package pl.kkorzycki.schooldb;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.*;

public class LoginWindow {

    private DBConnector dbConnector;

    private static Scene scene;

    @FXML
    private TextField textField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    @FXML
    private void loginButtonHandler (MouseEvent mouseEvent) {
        String login = textField.getText();
        String password = passwordField.getText();
        boolean isLoggedIn = false;
        dbConnector = DBConnector.getInstance();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:1433/szkola", login, password);
            dbConnector.setConnection(connection);

            isLoggedIn = true;
        } catch (SQLException ex) {
            textField.clear();
            passwordField.clear();
            errorLabel.setText("BŁĘDNE HASŁO LUB LOGIN!");
        }
        if (isLoggedIn) {
            try {
                String query = "CALL checkPrivileges('" + textField.getText() + "');";
                PreparedStatement statement = dbConnector.getConnection().prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();
                Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
                while (resultSet.next()) {
                    switch (resultSet.getString("Uprawnienia")) {
                        case "administrator":
                            root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
                            break;
                        case "dyrektor" :
                            root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
                            break;
                        case "nauczyciel":
                            root = FXMLLoader.load(getClass().getResource("MainWindowForTeacher.fxml"));
                            break;
                        case "sekretariat":
                            root = FXMLLoader.load(getClass().getResource("MainWindowForOffice.fxml"));
                            break;
                    }
                }
                scene = new Scene(root);
                GUI.getPrimaryStage().setScene(scene);
                GUI.getPrimaryStage().show();
            } catch (IOException | SQLException ex) {
                System.err.println(ex.toString());
                System.exit(1);
            }
        }
    }

    public static Scene getScene () {
        return scene;
    }
}
