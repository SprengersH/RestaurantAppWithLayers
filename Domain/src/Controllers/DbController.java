package Controllers;

import DTO.ItemDTO;
import DTO.OrderDTO;
import Entities.Item;
import Entities.Order;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbController {

    Item dbm;
    Order dbo;
    ItemDTO itemDTO;
    OrderDTO orderDTO;
    ArrayList<ItemDTO> itemList;


    public DbController() {

    }

    public void test(ArrayList<ItemDTO> itemList) {

        for (ItemDTO item : itemList) {
            System.out.println(item);

        }
    }

    public void showAllMenuItems() {

    }

    public void showMenu(int menuNumber) {
        System.out.println("This method is gone");
    }

    public ArrayList<Item> loadMenuItems() {
        return null;
    }

    public void insertOrder(String orderID, double price, int tableNumber, int activeOrNot) {
        //ori.insertOrder(orderID, price, tableNumber, activeOrNot);
        //ori.insertOrderToProduct(dbo, dbo.getOrderedItems());
        //Main.run();
    }

    /* public String retrieveOrderID(int tableNumber) {
        String id = null;
        try {
            id = ori.getOrderidFromTablenumber(tableNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public List<Item> retrieveItemList(String orderID) {
        return ori.retrieveItemsFromOrder(orderID);
    }

    public void setAvailable(int tableNumber) {
        ori.setAvailable(tableNumber);
        // todo no sout's outside of ui, this should return one or more lists to the ui and the ui should print the statements.
        System.out.println("Table " + tableNumber + " is set available");
    }*/
}
