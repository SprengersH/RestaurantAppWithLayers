package Logic.Interfaces;

import Logic.Item;
import Logic.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderRepository {

    void insertOrder(String orderID, double price, int tableNumber, int activeOrNot);

    public void insertOrderToProduct(OrderRepository order, List<ItemRepository> orderedMenuMenuItems);

    String getOrderidFromTablenumber(int tableNumber) throws SQLException;

    List<Item> retrieveItemsFromOrder(String orderID);

    List<Integer> getProductIDs(String orderID);

    void setAvailable(int tableNumber);
}
