package Database;

import Interfaces.ItemRepository;
import DTO.ItemDTO;

import java.sql.*;
import java.util.ArrayList;

public class ItemDAL extends DatabasePath implements ItemRepository {


    public ArrayList<ItemDTO> getItems() {

        String query = "SELECT * FROM menuitem";
        ArrayList<ItemDTO> allMenuItemsList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                ItemDTO item = new ItemDTO
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

    public ArrayList<ItemDTO> selectMenu(int menunumber) {

        String query = "SELECT * FROM menuitem WHERE menunumber = ?";
        ArrayList<ItemDTO> itemList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            // Bind value into the statement at parameter index 1.
            stmt.setInt(1, menunumber);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ItemDTO item = new ItemDTO
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
