package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabasePath {

    static final String DB_URL = "jdbc:mysql://localhost:3306/bppdatabase";
    static final String USER = "Oefenacc";
    static final String PASSWORD = "Oefenacc";

    private static Connection connection;

//    static void openDatabaseConnection() throws SQLException {
//        System.out.println("Connecting to the database...");
//        connection = DriverManager.getConnection(
//                "jdbc:mysql://localhost:3306/bppdatabase",
//                "Oefenacc",
//                "Oefenacc");
//        System.out.println("Connection valid: " + connection.isValid(5));
//    }
//    static void closeDatabaseConnection() throws SQLException {
//        System.out.println("Closing the database connection...");
//        connection.close();
//        System.out.println("Connection valid: " + connection.isValid(5));
//    }

}


