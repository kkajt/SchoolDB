package pl.kkorzycki.schooldb.SelectingData;

import pl.kkorzycki.schooldb.GUI;
import pl.kkorzycki.schooldb.LoginWindow;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SelectDataChooserForTeacher extends SelectDataChooser {

    private String query;

    private SelectedDataViewer selectedDataViewer;

    @FXML
    private TextField textField;

    @FXML
    private Label infoLabel;

    @FXML
    private void selectAllStudentsHandler (MouseEvent mouseEvent) {
        query = "CALL selectAllStudents ()";
        System.out.println(query);
        selectedDataViewer = new SelectedDataViewer(query, this);
    }

    @FXML
    private void selectAllWorkesHandler (MouseEvent mouseEvent) {
        query = "CALL selectAllWorkers ()";
        selectedDataViewer = new SelectedDataViewer(query, this);
    }

    @FXML
    private void selectStudentsReducedHandler (MouseEvent mouseEvent) {
        query = "CALL selectStudentsReduced ()";
        selectedDataViewer = new SelectedDataViewer(query, this);
    }

    @FXML
    private void selectClassHandler (MouseEvent mouseEvent) {
        infoLabel.setText("");
        String code = textField.getText();
        query = "CALL selectClass ('" + code + "')";
        selectedDataViewer = new SelectedDataViewer(query, this);
    }

    @FXML
    private void selectClassSubjectsHandler (MouseEvent mouseEvent) {
        infoLabel.setText("");
        String code = textField.getText();
        query = "CALL selectClassSubjects ('" + code + "')";
        selectedDataViewer = new SelectedDataViewer(query, this);
    }

    @FXML
    private void selectTeachersHandler (MouseEvent mouseEvent) {
        query = "CALL selectTeachers ()";
        selectedDataViewer = new SelectedDataViewer(query, this);
    }

    @FXML
    private void selectWorkersExceptTeachersHandler (MouseEvent mouseEvent) {
        query = "CALL selectWorkersExceptTeachers ()";
        selectedDataViewer = new SelectedDataViewer(query, this);
    }

    @FXML
    private void selectClassWithTutorsHandler (MouseEvent mouseEvent) {
        query = "CALL selectClassWithTutors ()";
        selectedDataViewer = new SelectedDataViewer(query, this);
    }

    @FXML
    private void selectTeacherInfoHandler (MouseEvent mouseEvent) {
        infoLabel.setText("");
        String code = textField.getText();
        query = "CALL selectTeacherInfo ('" + code + "')";
        selectedDataViewer = new SelectedDataViewer(query, this);
    }

    @FXML
    public void backToStartingWindowHandler (MouseEvent mouseEvent) {
        GUI.getPrimaryStage().setScene(LoginWindow.getScene());
    }

    public Label getInfoLabel() {
        return infoLabel;
    }

    public TextField getTextField() {
        return textField;
    }
}
