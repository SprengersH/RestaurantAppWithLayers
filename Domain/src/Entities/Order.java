package Entities;

import Interfaces.DiscountRules;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Order {

    private String orderID;
    private List<Item> items;
    private double totalPrice;
    private int tableNumber;
    private int active;
    private LocalDate orderDate;


    public Order(int tableNumber, List<Item> items) {
        this.orderID = UUID.randomUUID().toString();
        this.items = items;
        for (Item item : items) {
            this.totalPrice += item.getPrice();
        }
        this.tableNumber = tableNumber;
        this.active = 1;
        this.orderDate = LocalDate.now();
    }

    public Order(String orderID, double totalPrice, int tableNumber, int active) {
        this.orderID = orderID;
        this.totalPrice = totalPrice;
        this.tableNumber = tableNumber;
        this.active = active;
    }

    public Order(int tableNumber, String orderID, List<Item> items) {
        this.orderID = orderID;
        this.items = items;
        for (Item item : items) {
            this.totalPrice += item.getPrice();
        }
        this.tableNumber = tableNumber;
        this.active = 1;
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

        return "Your Order: \n" +
                items +
                "\n TotalPrice: " + totalPrice;
    }


    public void getPriceAfterDiscounts(List<DiscountRules> discountRules) {
        double totalDiscount = 0;
        for (DiscountRules discounters : discountRules) {
            totalDiscount += discounters.applyDiscount();
        }
        this.totalPrice -= (totalPrice / 100 * totalDiscount);
    }

    public LocalDate getDate() {
        return this.orderDate;
    }
}
