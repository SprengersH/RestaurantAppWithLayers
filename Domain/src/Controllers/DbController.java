package Controllers;

import Entities.Item;
import Interfaces.ItemRepository;
import Interfaces.OrderRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbController {

    private OrderRepository orderRepo; // a list of all orders, coming from DAL or Mock.
    private ItemRepository itemRepo; // a list of all items, coming from DAL or Mock.

    public DbController(ItemRepository itemRepo, OrderRepository orderRepo) {
        this.itemRepo = itemRepo;
        this.orderRepo = orderRepo;
    }

    public ArrayList<Item> getAllItems() {
        ArrayList<Item> items = itemRepo.getItems();
        return items;
    }

    public void test(ArrayList<Item> itemList) {

        for (Item item : itemList) {
            System.out.println(item);
        }
    }

    public void showAllMenuItems() {

    }

    public void showMenu(int menuNumber) {
        System.out.println("This method is gone");
    }

    public ArrayList<Entities.Item> loadMenuItems() {
        return null;
    }

    public void insertOrder(String orderID, double price, int tableNumber, int activeOrNot) {
        orderRepo.insertOrder(orderID, price, tableNumber, activeOrNot);
        //orderRepo.insertOrderToProduct(dbo, dbo.getOrderedItems());

    }

    public String retrieveOrderID(int tableNumber) {
        String id = null;
        id = orderRepo.getOrderidFromTablenumber(tableNumber);
        return id;
    }

    public List<Item> retrieveItemList(String orderID) {
        return orderRepo.retrieveItemsFromOrder(orderID);
    }

    public void setAvailable(int tableNumber) {
        orderRepo.setAvailable(tableNumber);
        // todo no sout's outside of ui, this should return one or more lists to the ui and the ui should print the statements.
        System.out.println("Table " + tableNumber + " is set available");
    }
}
