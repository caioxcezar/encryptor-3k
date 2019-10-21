package utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoUtils {
    public static void closeResources(Connection conn, Statement st) throws SQLException {
        try {
            if (st != null) {
                st.close();
            }
            if (conn != null) {
                conn.close();
            }
        } finally {

        }
    }
}