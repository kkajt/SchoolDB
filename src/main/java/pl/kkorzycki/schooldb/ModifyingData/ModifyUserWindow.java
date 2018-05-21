package pl.kkorzycki.schooldb.ModifyingData;

import pl.kkorzycki.schooldb.GUI;
import pl.kkorzycki.schooldb.LoginWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;

public class ModifyUserWindow extends ModifyWindow {

    @FXML
    private TextField login;

    @FXML
    private TextField column;

    @FXML
    private TextField value;

    @FXML
    private Label label;

    public ModifyUserWindow () {
        super();
    }

    @FXML
    private void modifyUserFromDatabaseHandler (MouseEvent mouseEvent) {
        query = "CALL modifyUser ('" + login.getText() + "','" + column.getText() +
                "','" + value.getText() + "')";
        try {
            label.setText("");
            statement = connection.prepareStatement(query);
            statement.executeQuery();
            clearTextFields();
        } catch (SQLException ex) {
            label.setText("Nie można dokonać zmian!");
        }
    }

    @FXML
    public void backToStartingWindowHandler (ActionEvent actionEvent) {
        GUI.getPrimaryStage().setScene(LoginWindow.getScene());
    }

    private void clearTextFields () {
        login.clear();
        column.clear();
        value.clear();
    }
}
