package pl.kkorzycki.schooldb.ModifyingData;

import pl.kkorzycki.schooldb.GUI;
import pl.kkorzycki.schooldb.LoginWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;

public class ModifySubjectWindow extends ModifyWindow {
    @FXML
    private TextField subjectsName;

    @FXML
    private TextField code;

    @FXML
    private TextField column;

    @FXML
    private TextField value;

    @FXML
    private Label label;

    public ModifySubjectWindow () {
        super();
    }

    @FXML
    private void modifySubjectFromDatabaseHandler (MouseEvent mouseEvent) {
        query = "CALL modifySubject ('" + subjectsName.getText() + "','" +
                code.getText() + "','" + column.getText() + "','" +
                value.getText() + "')";
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
        subjectsName.clear();
        code.clear();
        column.clear();
        value.clear();
    }

}
