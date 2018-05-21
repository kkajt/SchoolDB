package pl.kkorzycki.schooldb.ModifyingData;

import pl.kkorzycki.schooldb.GUI;
import pl.kkorzycki.schooldb.LoginWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;

public class ModifyClassWindow extends ModifyWindow {

    @FXML
    private TextField code;

    @FXML
    private TextField column;

    @FXML
    private TextField value;

    @FXML
    private Label label;

    public ModifyClassWindow () {
        super();
    }

    @FXML
    private void modifyClassFromDatabaseHandler (MouseEvent mouseEvent) {
        query = "CALL modifyClass ('" + code.getText() + "','" + column.getText() +
                "','" + value.getText() + "')";
        try {
            label.setText("");
            statement = connection.prepareStatement(query);
            statement.executeQuery();
            clearTextFields();
        } catch (SQLException ex) {
            label.setText("Nie można dodkonać zmian!");
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
