package infrastructure.Database;

import domain.Entities.Item;
import domain.Interfaces.ItemRepository;

import java.sql.*;
import java.util.ArrayList;

public class ItemDAL implements ItemRepository {


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

    public ArrayList<Item> getAllItems() {
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
