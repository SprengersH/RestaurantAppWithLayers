package Database;

import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderData extends Database {


    // this should be FED the data instead of pushing it, and just return the data required.


    public ArrayList<OrderDTO> retrieveItemsFromOrder(String orderID, ArrayList<Integer> productIDList) {

        String query = "SELECT * FROM menuitem WHERE menuitemid = ?";
        ArrayList<OrderDTO> listToBill = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query);
        ) {
            // Bind value into the statement at parameter index 1.
            for (int productID : productIDList) {

                stmt.setInt(1, productID);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    OrderDTO menuItem = new OrderDTO
                            (rs.getInt("menuitemid"),
                                    rs.getInt("menunumber"),
                                    rs.getString("coursetype"),
                                    rs.getString("name"),
                                    rs.getString("description"),
                                    rs.getDouble("price"));
                    listToBill.add(menuItem);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listToBill;
    }

    public void insertOrder(String orderID, double price, int tableNumber, int activeOrNot) {
        String query = "INSERT INTO `orders` (orderid, orderprice, tablenumber, active) VALUES (?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             // Generate a prepared statement with the placeholder parameter.
             PreparedStatement stmt = conn.prepareStatement(query);
        ) {
            // Bind value into the statement at parameter index 1,2,3.
            stmt.setString(1, orderID);
            stmt.setDouble(2, price);
            stmt.setInt(3, tableNumber);
            stmt.setInt(4, activeOrNot);
            // Execute a query

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
