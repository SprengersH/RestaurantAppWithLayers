package Database;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBOrders extends Database {


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


    public void insertOrderToProduct(Order order, List<ItemInterfacer> orderedMenuMenuItems) {
        String query = "INSERT INTO `order-product` (orderid, productid, tablenumber, active) VALUES (?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             // Generate a prepared statement with the placeholder parameter.
             PreparedStatement stmt = conn.prepareStatement(query);
        ) {
            // Bind value into the statement at parameter index 1,2,3,4.
            for (ItemInterfacer menuItem : order.getOrderedItems()) {
                stmt.setString(1, order.getOrderID());
                stmt.setInt(2, menuItem.getMenuItemID());
                stmt.setInt(3, order.getTableNumber());
                stmt.setInt(4, order.getActive());
                // Execute a query

                stmt.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public String getOrderidFromTablenumber(int tableNumber) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        // Generate a prepared statement with the placeholder parameter.
        String query = "SELECT orderid FROM `order-product` WHERE tablenumber = ? AND active = ?";
        PreparedStatement stmt = conn.prepareStatement(query);

        // Bind value into the statement at parameter index 1 etc.

        stmt.setInt(1, tableNumber);
        stmt.setInt(2, 1);
        ResultSet rs = stmt.executeQuery();
        // don't forget to execute the PrepareStatement without parameters (stmt.executeQuery(query))
        // or you will lose another 5 hours of your life.
        String orderID = "";
        while (rs.next()) {
            orderID = rs.getString("orderid");
        }
        return orderID;
    }


    public List<OrderDTO> retrieveItemsFromOrder(String orderID) {

        List<Integer> productIDList = getProductIDs(orderID);

        String query = "SELECT * FROM menuitem WHERE menuitemid = ?";
        List<OrderDTO> listToBill = new ArrayList<>();

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


    public List<Integer> getProductIDs(String orderID) {
        String query = "SELECT productid FROM `order-product` WHERE orderid = ?";
        List<Integer> productIDList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query);
        ) {
            // Bind value into the statement at parameter index 1.
            stmt.setString(1, orderID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int productID = rs.getInt("productid");

                productIDList.add(productID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productIDList;
    }


    public void setAvailable(int tableNumber) {
        String query = "UPDATE orders SET active=0 WHERE tablenumber = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query);
        ) {
            stmt.setInt(1, tableNumber);
            // dont forget to use the executeupdate command, or you will lose another 2 hours trying to fix a query where nothing is wrong..
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        String query2 = "UPDATE `order-product` SET active=0 WHERE tablenumber = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query2);
        ) {
            stmt.setInt(1, tableNumber);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

