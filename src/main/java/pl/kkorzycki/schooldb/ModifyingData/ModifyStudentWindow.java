package pl.kkorzycki.schooldb.ModifyingData;

import pl.kkorzycki.schooldb.GUI;
import pl.kkorzycki.schooldb.LoginWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;

public class ModifyStudentWindow extends ModifyWindow {

    @FXML
    private TextField code;

    @FXML
    private TextField column;

    @FXML
    private TextField value;

    @FXML
    private Label label;

    public ModifyStudentWindow () {
        super();
    }

    @FXML
    private void modifyStudentFromDatabaseHandler (MouseEvent mouseEvent) {
        query = "CALL modifyStudent ('" + code.getText() + "','" + column.getText() +
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
        code.clear();
        column.clear();
        value.clear();
    }
}
