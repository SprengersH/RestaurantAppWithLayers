package Entities;

import Interfaces.DiscountRules;

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
            if (numberOfBeers > 5) {
                return 10;
            }
        }
        return 0;
    }
}

