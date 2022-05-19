package Entities;


import java.util.List;
import java.util.*;

// could bill implement an interface like Payable???
public class Bill {

    private int tableNumber;
    private String orderID;
    private double totalBillPrice;
    private List<Item> menuItemToBill;


    public Bill(int tableNumber, String orderID, List<Item> menuItemToBill) {

        this.tableNumber = tableNumber;
        this.orderID = orderID;
        for (Item menuItem : menuItemToBill) {
            this.totalBillPrice += menuItem.getPrice();
        }
        this.menuItemToBill = menuItemToBill;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public String getOrderID() {
        return orderID;
    }

    public double getTotalBillPrice() {
        return totalBillPrice;
    }

    public List<Item> getMenuItemToBill() {
        return menuItemToBill;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "tableNumber=" + tableNumber +
                ", orderID='" + orderID + '\'' +
                ", totalBillPrice=" + totalBillPrice +
                ", itemToBill=" + menuItemToBill +
                '}';
    }
}

