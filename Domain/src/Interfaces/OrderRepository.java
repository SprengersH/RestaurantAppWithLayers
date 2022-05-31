package Interfaces;

import Entities.Item;
import Entities.Order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface OrderRepository {

    ArrayList<Order> getOrders();

    void insertOrder(String orderID, double price, int tableNumber, int activeOrNot, LocalDate date);

    void insertOrderToProduct(Order order, List<Item> orderedMenuMenuItems);

    String getOrderidFromTablenumber(int tableNumber);

    List<Item> retrieveItemsFromOrder(String orderID);

    List<Integer> getProductIDs(String orderID);

    void setAvailable(int tableNumber);

    void updateOrder(Order order);

    void getSales(LocalDate timePeriod);
}
