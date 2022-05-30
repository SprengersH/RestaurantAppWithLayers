package Controllers;

import Entities.Item;
import Entities.Order;
import Interfaces.ItemRepository;
import Interfaces.OrderRepository;

import java.util.List;

public class OrderController {

    private OrderRepository orderRepo;
    List<Order> orders;

    public OrderController(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    public List<Order> getOrders() {
        orders = orderRepo.getOrders();
        return orders;
    }

    public void addItemsToOrder(List<Item> itemsToAdd, int tableNumber) {
        Order order = new Order(tableNumber, itemsToAdd);
        addOrderToDatabase(order);
        linkOrderToProduct(order, itemsToAdd);
        itemsToAdd.clear();
    }

    public void linkOrderToProduct(Order order, List<Item> orderedMenuMenuItems){
        orderRepo.insertOrderToProduct(order, orderedMenuMenuItems);
    }


    public void addOrderToDatabase(Order order) {
       orderRepo.insertOrder(order.getOrderID(), order.getPrice(), order.getTableNumber(), order.getActive());
    }

    public String retrieveOrderID(int tableNumber) {
        String id;
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

