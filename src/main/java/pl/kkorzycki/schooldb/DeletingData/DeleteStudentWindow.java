package pl.kkorzycki.schooldb.DeletingData;

import pl.kkorzycki.schooldb.GUI;
import pl.kkorzycki.schooldb.LoginWindow;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;

public class DeleteStudentWindow extends DeleteWindow {


    @FXML
    private TextField studentsCode;

    @FXML
    private Label label;

    public DeleteStudentWindow () {
        super();
    }

    @FXML
    private void deleteStudentFromDatabaseHandler (MouseEvent mouseEvent) {
        query = "CALL deleteStudent('" + studentsCode.getText() + "')";
        try {
            label.setText("");
            statement = connection.prepareStatement(query);
            statement.executeQuery();
            studentsCode.clear();
        } catch (SQLException ex) {
            label.setText("Nie można usunąć ucznia!");
        }
    }

    @FXML
    @Override
    protected void backToStartingWindowHandler (MouseEvent mouseEvent) {
        GUI.getPrimaryStage().setScene(LoginWindow.getScene());
    }

}
