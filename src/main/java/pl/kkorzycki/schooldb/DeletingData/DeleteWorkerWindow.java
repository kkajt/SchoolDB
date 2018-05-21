package pl.kkorzycki.schooldb.DeletingData;

import pl.kkorzycki.schooldb.GUI;
import pl.kkorzycki.schooldb.LoginWindow;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;

public class DeleteWorkerWindow extends DeleteWindow {

    @FXML
    private TextField workersCode;

    @FXML
    private Label label;

    public DeleteWorkerWindow () {
        super();
    }

    @FXML
    private void deleteWorkerFromDatabaseHandler (MouseEvent mouseEvent) {
        query = "CALL deleteWorker('" + workersCode.getText() + "')";
        try {
            label.setText("");
            statement = connection.prepareStatement(query);
            statement.executeQuery();
            workersCode.clear();
        } catch (SQLException ex) {
            label.setText("Nie można usunąć pracownika!");
        }
    }

    @FXML
    @Override
    protected void backToStartingWindowHandler (MouseEvent mouseEvent) {
        GUI.getPrimaryStage().setScene(LoginWindow.getScene());
    }
}
