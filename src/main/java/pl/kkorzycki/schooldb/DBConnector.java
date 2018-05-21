package pl.kkorzycki.schooldb;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DBConnector {

    private Connection connection;

    private PreparedStatement statement;

    private static DBConnector instance;

    private DBConnector () {

    }

    public static DBConnector getInstance () {
        if (instance == null) {
            instance = new DBConnector();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public PreparedStatement getStatement() {
        return statement;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setStatement(PreparedStatement statement) {
        this.statement = statement;
    }
}
