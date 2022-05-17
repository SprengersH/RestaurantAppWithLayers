package Logic;

import Logic.Interfaces.ItemRepository;
import Logic.Interfaces.OrderRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class OrderDTO {

    private String orderID;
    private List<Item> items;
    private double totalPrice;
    private int tableNumber;

    private int active;
    // sql wouldn't let me use boolean and didn't want to use tinyint.
    // 1 for active 0 for inactive

    public OrderDTO(int tableNumber, List<Item> items) {
        this.orderID = UUID.randomUUID().toString();
        this.items = items;

        for (Item item : items) {
            this.totalPrice += item.getPrice();
        }
        this.tableNumber = tableNumber;
        this.active = 1;
    }

    public OrderDTO(String orderID, List<Item> orderedItems, double totalPrice, int tableNumber, int active) {
        this.orderID = orderID;
        this.items = orderedItems;
        this.totalPrice = totalPrice;
        this.tableNumber = tableNumber;
        this.active = active;
    }
}
