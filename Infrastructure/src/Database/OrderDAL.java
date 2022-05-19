package Database;

import Entities.Order;
import Interfaces.ItemRepository;
import Interfaces.OrderRepository;
import Entities.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAL extends DatabasePath implements OrderRepository {

    Connection connection;

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
    public ArrayList<Order> getOrders() {
        System.out.println("Reading data...");
        try {
            openDatabaseConnection();
            ArrayList<Order> allOrderList = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM orders");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Order order = new Order
                        (rs.getString("orderid"),
                                rs.getDouble("orderprice"),
                                rs.getInt("tablenumber"),
                                rs.getInt("active"));

                allOrderList.add(order);
            }
            closeDatabaseConnection();
            return allOrderList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertOrder(String orderID, double price, int tableNumber, int activeOrNot) {
        System.out.println("Creating data...");
        try {
            openDatabaseConnection();
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO `orders` (orderid, orderprice, tablenumber, active)VALUES(?,?,?,?)")) {
                statement.setString(1, orderID);
                statement.setDouble(2, price);
                statement.setInt(3, tableNumber);
                statement.setInt(4, activeOrNot);

                int rowsInserted = statement.executeUpdate();
                System.out.println("Rows inserted: " + rowsInserted);
            }
            closeDatabaseConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void insertOrderToProduct(Order orderToLink, List<Item> itemsToLink) {
        System.out.println("Creating data...");
        try {
            openDatabaseConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO `order-product` (orderid, productid, tablenumber, active) VALUES (?,?,?,?)")) {
            for (Item Item : orderToLink.getOrderedItems()) {
                statement.setString(1, orderToLink.getOrderID());
                statement.setInt(2, Item.getMenuItemID());
                statement.setInt(3, orderToLink.getTableNumber());
                statement.setInt(4, orderToLink.getActive());

                int rowsInserted = statement.executeUpdate();
                System.out.println("Rows inserted: " + rowsInserted);
            }
            closeDatabaseConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

// todo better data queries below this line needed
    public String getOrderidFromTablenumber(int tableNumber) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Item> retrieveItemsFromOrder(String orderID) {

        List<Integer> productIDList = getProductIDs(orderID);

        String query = "SELECT * FROM menuitem WHERE menuitemid = ?";
        List<Item> listToBill = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD); PreparedStatement stmt = conn.prepareStatement(query);) {
            // Bind value into the statement at parameter index 1.
            for (int productID : productIDList) {

                stmt.setInt(1, productID);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Item menuItem = new Item(rs.getInt("menuitemid"), rs.getInt("menunumber"), rs.getString("coursetype"), rs.getString("name"), rs.getString("description"), rs.getDouble("price"));
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

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD); PreparedStatement stmt = conn.prepareStatement(query);) {
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
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD); PreparedStatement stmt = conn.prepareStatement(query);) {
            stmt.setInt(1, tableNumber);
            // dont forget to use the executeupdate command, or you will lose another 2 hours trying to fix a query where nothing is wrong..
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        String query2 = "UPDATE `order-product` SET active=0 WHERE tablenumber = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD); PreparedStatement stmt = conn.prepareStatement(query2);) {
            stmt.setInt(1, tableNumber);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
