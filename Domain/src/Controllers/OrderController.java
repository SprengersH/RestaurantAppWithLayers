package Controllers;

import Entities.Item;
import Entities.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderController {

    private ArrayList<Order> orders;
    private DbController dbc;


    public OrderController(DbController dbc, ArrayList<Order> orders) {
        this.orders = orders;
        this.dbc = dbc;
    }

    public void addToOrder(List<Item> itemsToAdd, int tableNumber) {
        Order order = new Order(tableNumber, itemsToAdd);
        addOrderToDatabase(order);
        itemsToAdd.clear();
    }

    public void addOrderToDatabase(Order order) {
       dbc.insertOrder(order.getOrderID(), order.getPrice(), order.getTableNumber(), order.getActive());
    }


}

