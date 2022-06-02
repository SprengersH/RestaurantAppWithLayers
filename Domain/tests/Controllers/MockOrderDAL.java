package Controllers;

import Entities.Item;
import Entities.Order;
import Interfaces.OrderRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MockOrderDAL implements OrderRepository {

    @Override
    public ArrayList<Order> getOrders() {
        List<Item> items = new ArrayList<>();
        Item item1 = new Item(1, 1, "DRINKS", "Bier", "testBier", 2.0);
        Item item2 = new Item(1, 1, "MAIN COURSE", "Snitzel", "testSnitzel", 2.0);
        Item item3 = new Item(1, 1, "SIDE DISH", "Friet", "testFriet", 2.0);
        items.add(item1);
        items.add(item2);
        items.add(item3);
        ArrayList<Order> mockOrders = new ArrayList<>();
        Order order1 = new Order(1, items);
        mockOrders.add(order1);
        return mockOrders;
    }

    @Override
    public void insertOrder(String orderID, double price, int tableNumber, int activeOrNot, LocalDate date) {

    }

    @Override
    public void insertOrderToProduct(Order order, List<Item> orderedMenuMenuItems) {

    }

    @Override
    public String getOrderidFromTablenumber(int tableNumber) {
        return null;
    }

    @Override
    public List<Item> retrieveItemsFromOrder(String orderID) {
        return null;
    }

    @Override
    public List<Integer> getProductIDs(String orderID) {
        return null;
    }

    @Override
    public void setAvailable(int tableNumber) {

    }

    @Override
    public void updateOrder(Order order) {

    }

    @Override
    public void getSales(LocalDate timePeriod) {

    }
}
