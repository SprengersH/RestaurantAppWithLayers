package Logic;

import java.util.List;
import java.util.UUID;

public class Order {

    private String orderID;
    private List<Item> items;
    private double totalPrice;
    private int tableNumber;

    private int active;
    // sql wouldn't let me use boolean and didn't want to use tinyint.
    // 1 for active 0 for inactive

    public Order(int tableNumber, List<Item> items) {
        this.orderID = UUID.randomUUID().toString();
        this.items = items;

        for (Item item : items) {
            this.totalPrice += item.getPrice();
        }
        this.tableNumber = tableNumber;
        this.active = 1;
    }

    public Order(String orderID, List<Item> orderedItems, double totalPrice, int tableNumber, int active) {
        this.orderID = orderID;
        this.items = orderedItems;
        this.totalPrice = totalPrice;
        this.tableNumber = tableNumber;
        this.active = active;
    }


    public int getActive() {
        return active;
    }


    public void setActive(int active) {
        if (active < 0 || active > 1) {
            this.active = 0;
        } else {
            this.active = active;
        }
    }


    public List<Item> getOrderedItems() {
        return items;
    }


    public double getPrice() {
        return totalPrice;
    }


    public int getTableNumber() {
        return tableNumber;
    }


    public String getOrderID() {
        return orderID;
    }


    public double getTotalPrice() {
        return totalPrice;
    }


    public String toString() {
        return "Order{" +
                "orderedMenuItems=" + items +
                ", totalPrice=" + totalPrice +
                ", tableNumber=" + tableNumber + ", active=" + active +
                '}';
    }


}
