package pl.kkorzycki.schooldb.DeletingData;

import pl.kkorzycki.schooldb.GUI;
import pl.kkorzycki.schooldb.LoginWindow;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;

public class DeleteUserWindow extends DeleteWindow {

    @FXML
    private TextField userCode;

    @FXML
    private Label label;

    public DeleteUserWindow () {
        super();
    }

    @FXML
    private void deleteUserFromDatabaseHandler (MouseEvent mouseEvent) {
        query = "CALL deleteUser('" + userCode.getText() + "')";
        try {
            label.setText("");
            statement = connection.prepareStatement(query);
            statement.executeQuery();
            userCode.clear();
        } catch (SQLException ex) {
            label.setText("Nie można usunąć użytkownika");
        }
    }

    @FXML
    @Override
    protected void backToStartingWindowHandler (MouseEvent mouseEvent) {
        GUI.getPrimaryStage().setScene(LoginWindow.getScene());
    }
}
