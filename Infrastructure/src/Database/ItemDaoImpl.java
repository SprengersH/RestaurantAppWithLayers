package Database;

import Logic.Interfaces.ItemRepository;
import Logic.Item;

import java.sql.*;
import java.util.ArrayList;

public class ItemDaoImpl extends Database implements ItemRepository {

    private ItemRepository itemrepository;

    public ArrayList<Item> GetItems() {

        String query = "SELECT * FROM menuitem";
        ArrayList<Item> allMenuItemsList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allMenuItemsList;
    }
    public ArrayList<Item> selectMenu(int menunumber) {

        String query = "SELECT * FROM menuitem WHERE menunumber = ?";
        ArrayList<Item> itemList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            // Bind value into the statement at parameter index 1.
            stmt.setInt(1, menunumber);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Item item = new Item
                        (rs.getInt("menuitemid"),
                                rs.getInt("menunumber"),
                                rs.getString("coursetype"),
                                rs.getString("name"),
                                rs.getString("description"),
                                rs.getDouble("price"));
                itemList.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemList;
    }
}
