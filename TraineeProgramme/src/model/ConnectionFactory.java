package model;

import java.sql.Connection;

import com.mysql.cj.jdbc.Driver;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL = "jdbc:mysql://localhost/trainee_programm_db?";
    private static final String USER = "root";
    private static final String PWD = "root";

    public static Connection getConnection() {
        try {
            DriverManager.registerDriver(new Driver());

            return DriverManager.getConnection(URL
                    + "userUnicode=true&useJDBCCompliantTimezoneShift=true"
                    + "&user=" + USER + "&password=" + PWD + "&userSSL=false&allowPublicKeyRetrieval=true");
        } catch (SQLException e) {
            throw new RuntimeException("Error connection to the db", e);
        }

    }

    /**
     * Test Connection
     */

    public static void main(String[] args) {
        Connection connection = ConnectionFactory.getConnection();
    }


}
