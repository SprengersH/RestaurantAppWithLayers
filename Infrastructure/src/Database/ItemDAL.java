package Database;

import Entities.Item;
import Interfaces.ItemRepository;

import java.sql.*;
import java.util.ArrayList;

public class ItemDAL implements ItemRepository {


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

    public ArrayList<Item> getItems() {
        System.out.println("Reading data...");
        try {
            openDatabaseConnection();
            ArrayList<Item> allMenuItemsList = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM menuitem");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Item item = new Item
                        (rs.getInt("menuitemid"),
                                rs.getInt("menunumber"),
                                rs.getString("coursetype"),
                                rs.getString("name"),
                                rs.getString("description"),
                                rs.getDouble("price"));
                allMenuItemsList.add(item);
            }
            closeDatabaseConnection();
            return allMenuItemsList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Item> selectMenu(int menunumber) {
        System.out.println("Reading data...");
        try {
            openDatabaseConnection();
            ArrayList<Item> itemsList = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM menuitem WHERE menunumber = ?");
            statement.setInt(1, menunumber);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Item item = new Item
                        (rs.getInt("menuitemid"),
                                rs.getInt("menunumber"),
                                rs.getString("coursetype"),
                                rs.getString("name"),
                                rs.getString("description"),
                                rs.getDouble("price"));
                itemsList.add(item);
            }
            closeDatabaseConnection();
            return itemsList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
