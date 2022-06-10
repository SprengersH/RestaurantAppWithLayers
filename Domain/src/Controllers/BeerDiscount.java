package Controllers;

import Entities.Item;
import Entities.Order;
import Interfaces.DiscountRules;

import java.util.List;

public class BeerDiscount implements DiscountRules {

    Order order;

    public BeerDiscount(Order order) {
        this.order = order;
    }

    @Override
    public double applyDiscount() {

        int numberOfBeers = 0;

        for (Item item : order.getOrderedItems()) {
            if (item.getMenuItemID() == 5) {
                numberOfBeers++;
            }
            if (numberOfBeers >= 5) {
                return 10;
            }
        }
        return 0;
    }

    @Override
    public double applyDiscountBetter(List<Item> items) {
        int numberOfBeers = 0;

        for (Item item : items) {
            if (item.getMenuItemID() == 5) {
                numberOfBeers++;
            }
            if (numberOfBeers >= 5) {
                return 10;
            }
        }
        return 0;
    }
}

