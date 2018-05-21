package pl.kkorzycki.schooldb.DeletingData;

import pl.kkorzycki.schooldb.GUI;
import pl.kkorzycki.schooldb.LoginWindow;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;

public class DeleteClassWindow extends DeleteWindow {

    @FXML
    private TextField classCode;

    @FXML
    private Label label;

    public DeleteClassWindow () {
        super();
    }

    @FXML
    private void deleteClassFromDatabaseHandler (MouseEvent mouseEvent) {
        query = "CALL deleteClass('" + classCode.getText() + "')";
        try {
            label.setText("");
            statement = connection.prepareStatement(query);
            statement.executeQuery();
            classCode.clear();
        } catch (SQLException ex) {
            label.setText("Nie można usunąć klasy!");
        }
    }

    @FXML
    @Override
    protected void backToStartingWindowHandler (MouseEvent mouseEvent) {
        GUI.getPrimaryStage().setScene(LoginWindow.getScene());
    }

}
