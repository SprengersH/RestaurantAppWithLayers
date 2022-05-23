package Database;

import Entities.Bill;
import Interfaces.BillRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BillDAL implements BillRepository {

    private static Connection connection;

    private void openDatabaseConnection() throws SQLException {
        System.out.println("Connecting to the database...");
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bppdatabase",
                "Oefenacc",
                "Oefenacc");
        System.out.println("Connection valid: " + connection.isValid(5));
    }

    private void closeDatabaseConnection() throws SQLException {
        System.out.println("Closing the database connection...");
        connection.close();
        System.out.println("Connection valid: " + connection.isValid(5));
    }

    @Override
    public void insertBill(Bill bill) {
        System.out.println("Creating data...");
        try {
            openDatabaseConnection();
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO `bills` (bill_id, billprice, tablenumber)VALUES(?,?,?)")) {
                statement.setString(1, bill.getOrderID());
                statement.setDouble(2, bill.getTotalBillPrice());
                statement.setInt(3, bill.getTableNumber());
                int rowsInserted = statement.executeUpdate();
                System.out.println("Rows inserted: " + rowsInserted);
            }
            closeDatabaseConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
