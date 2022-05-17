package Logic;

import Logic.Interfaces.DBOrdersInterfacer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbController {

    ItemDao dbm;
    DBOrdersInterfacer dbo;


    public DbController() {

    }

    public void test() {
        dbm.selectAllMenuItems();
    }

    public void showAllMenuItems() {
        System.out.println(dbm.selectAllMenuItems());
    }

    public void showMenu(int menuNumber) {
        System.out.println(dbm.selectMenu(menuNumber));
    }

    public ArrayList<ItemDao> loadMenuItems() {
        return dbm.selectAllMenuItems();
    }

    public void insertOrder(Order order) {
        dbo.insertOrder(order);
        dbo.insertOrderToProduct(order, order.getOrderedItems());
        //Main.run();
    }

    public String retrieveOrderID(int tableNumber) {
        String id = null;
        try {
            id = dbo.getOrderidFromTablenumber(tableNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public List<ItemDao> retrieveItemList(String orderID) {
        return dbo.retrieveItemsFromOrder(orderID);
    }

    public void setAvailable(int tableNumber) {
        dbo.setAvailable(tableNumber);
        // todo no sout's outside of ui, this should return one or more lists to the ui and the ui should print the statements.
        System.out.println("Table " + tableNumber + " is set available");
    }
}
