package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseLocator {
    private final static dao.DataBaseLocator INSTANCE = new dao.DataBaseLocator();

    public static dao.DataBaseLocator getInstance() {
        return INSTANCE;
    }

    private DataBaseLocator() {
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/encryptor", "encryptor", "");
        return conn;
    }

}
