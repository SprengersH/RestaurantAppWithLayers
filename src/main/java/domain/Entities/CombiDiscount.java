package domain.Entities;

import domain.Interfaces.DiscountRules;

public class CombiDiscount implements DiscountRules {

    Order order;

    public CombiDiscount(Order order) {
        this.order = order;
    }

    @Override
    public double applyDiscount() {

        boolean drinks = false;
        boolean main = false;
        boolean side = false;

        for (Item item : order.getOrderedItems()) {
            if (item.getCourseType().equalsIgnoreCase("Drinks")) {
                drinks = true;
            }
            if (item.getCourseType().equalsIgnoreCase("main course")) {
                main = true;
            }
            if (item.getCourseType().equalsIgnoreCase("side dish")) {
                side = true;
            }
        }
        if (drinks && main && side) {
            return 10;
        }
        return 0;
    }
}
