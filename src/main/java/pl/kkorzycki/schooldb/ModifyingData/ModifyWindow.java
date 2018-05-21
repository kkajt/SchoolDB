package pl.kkorzycki.schooldb.ModifyingData;

import pl.kkorzycki.schooldb.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;

public abstract class ModifyWindow {

    protected DBConnector dbConnector;

    protected Connection connection;

    protected PreparedStatement statement;

    protected String query;

    public ModifyWindow () {
        dbConnector = DBConnector.getInstance();
        connection = dbConnector.getConnection();
        statement = dbConnector.getStatement();
    }

}
