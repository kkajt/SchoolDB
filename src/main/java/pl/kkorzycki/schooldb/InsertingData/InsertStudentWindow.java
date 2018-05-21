package pl.kkorzycki.schooldb.InsertingData;

import pl.kkorzycki.schooldb.DBConnector;
import pl.kkorzycki.schooldb.GUI;
import pl.kkorzycki.schooldb.LoginWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertStudentWindow {

    private DBConnector dbConnector;

    private Connection connection;

    private PreparedStatement statement;

    private String query;

    @FXML
    private TextField PESEL;

    @FXML
    private TextField name;

    @FXML
    private TextField secondName;

    @FXML
    private TextField surname;

    @FXML
    private TextField year;

    @FXML
    private TextField month;

    @FXML
    private TextField day;

    @FXML
    private TextField fathersName;

    @FXML
    private TextField mothersName;

    @FXML
    private TextField address;

    @FXML
    private TextField postCode1;

    @FXML
    private TextField postCode2;

    @FXML
    private TextField city;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField className;

    @FXML
    private Label label;

    public InsertStudentWindow () {
        dbConnector = DBConnector.getInstance();
        connection = dbConnector.getConnection();
        statement = dbConnector.getStatement();
    }

    @FXML
    private void addStudentToDatabaseHandler (MouseEvent mouseEvent) {
        query = "CALL addStudent ('" + PESEL.getText() + "','" + name.getText() +"','" +
                secondName.getText() + "','" + surname.getText() + "','" +
                year.getText() + "-" + month.getText() + "-" + day.getText() + "','" +
                fathersName.getText() + "','" + mothersName.getText() + "','" +
                address.getText() + "','" + postCode1.getText() + "-" + postCode2.getText() + "','" +
                city.getText() +"','" + phoneNumber.getText() + "','" + className.getText() + "')";
        try {
            label.setText("");
            statement = connection.prepareStatement(query);
            statement.executeQuery();
            clearTextFields();
        } catch (SQLException ex) {
            label.setText("Nie można dodać ucznia!");
        }
    }

    @FXML
    public void backToStartingWindowHandler (ActionEvent actionEvent) {
        GUI.getPrimaryStage().setScene(LoginWindow.getScene());
    }

    private void clearTextFields () {
        PESEL.clear();
        name.clear();
        secondName.clear();
        surname.clear();
        year.clear();
        month.clear();
        day.clear();
        fathersName.clear();
        mothersName.clear();
        address.clear();
        postCode1.clear();
        postCode2.clear();
        city.clear();
        phoneNumber.clear();
        className.clear();
    }
}
