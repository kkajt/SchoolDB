package pl.kkorzycki.schooldb.SelectingData;

import pl.kkorzycki.schooldb.DBConnector;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectedDataViewer {

    private TableView tableView;

    private DBConnector dbConnector;

    private PreparedStatement statement;

    private Connection connection;

    private ObservableList<ObservableList> data;

    private String query;

    private SelectDataChooser selectDataChooser;

    public SelectedDataViewer(String query, SelectDataChooser selectDataChooser) {
        dbConnector = DBConnector.getInstance();
        statement = dbConnector.getStatement();
        connection = dbConnector.getConnection();
        tableView = new TableView();
        this.query = query;
        this.selectDataChooser = selectDataChooser;
        showData();
        Scene scene = new Scene(tableView);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    private void showData () {
        try {
            statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            data = FXCollections.observableArrayList();

            int numberOfColumns = resultSet.getMetaData().getColumnCount();
            for (int i = 0; i < numberOfColumns; i++) {

                final int j = i;
                TableColumn col = new TableColumn(resultSet.getMetaData().getColumnName(i+1));
                col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));
                tableView.getColumns().add(col);
            }

            while (resultSet.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=resultSet.getMetaData().getColumnCount(); i++){
                    row.add(resultSet.getString(i));
                }
                data.add(row);
            }

            tableView.setItems(data);
        } catch (SQLException ex) {
            selectDataChooser.getInfoLabel().setText("Niepoprawne zapytanie!");
            selectDataChooser.getTextField().clear();
        }
    }
}
