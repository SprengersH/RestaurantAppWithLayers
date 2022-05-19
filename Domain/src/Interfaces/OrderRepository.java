package Interfaces;

import Entities.Item;
import Entities.Order;

import java.util.ArrayList;
import java.util.List;

public interface OrderRepository {

    ArrayList<Order> getOrders();

    void insertOrder(String orderID, double price, int tableNumber, int activeOrNot);

    void insertOrderToProduct(OrderRepository order, List<ItemRepository> orderedMenuMenuItems);

    String getOrderidFromTablenumber(int tableNumber);

    List<Item> retrieveItemsFromOrder(String orderID);

    List<Integer> getProductIDs(String orderID);

    void setAvailable(int tableNumber);
}
