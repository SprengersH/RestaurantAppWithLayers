package infrastructure.Database;

import domain.Entities.Bill;
import domain.Interfaces.BillRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BillDAL implements BillRepository {

    private static Connection connection;

    private void openDatabaseConnection() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bppdatabase",
                "Oefenacc",
                "Oefenacc");
    }

    private void closeDatabaseConnection() throws SQLException {
        connection.close();
    }

    @Override
    public void insertBill(Bill bill) {
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
