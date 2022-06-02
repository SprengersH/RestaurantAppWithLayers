package Controllers;

import Controllers.BeerDiscount;
import Entities.Item;
import Entities.Order;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class BeerDiscountTest {

    private List<Item> items = new ArrayList<>();

    @Test
    void ShouldApplyDiscount() {
        // given
        Item item1 = new Item(5, 1, "DRINKS", "Bier", "testBier", 2.0);
        Item item2 = new Item(5, 1, "DRINKS", "Bier", "testBier", 2.0);
        Item item3 = new Item(5, 1, "DRINKS", "Bier", "testBier", 2.0);
        Item item4 = new Item(5, 1, "DRINKS", "Bier", "testBier", 2.0);
        Item item5 = new Item(5, 1, "DRINKS", "Bier", "testBier", 2.0);
        Item item6 = new Item(5, 1, "DRINKS", "Bier", "testBier", 2.0);
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        items.add(item5);
        //items.add(item6);
        Order order = new Order(1, "1", items);
        // when
        double result = new BeerDiscount(order).applyDiscount();
        // then
        int expected = 10;
        assertEquals(expected, result);
    }
    @Test
    void ShouldNotApplyDiscount() {
        // given
        Item item1 = new Item(1, 1, "DRINKS", "Bier", "testBier", 2.0);
        Item item2 = new Item(1, 1, "SIDE DISH", "Friet", "testFriet", 2.0);
        Item item3 = new Item(1, 1, "SIDE DISH", "Friet", "testFriet", 2.0);
        items.add(item1);
        items.add(item2);
        items.add(item3);
        Order order = new Order(1, "1", items);
        // when
        double result = new BeerDiscount(order).applyDiscount();
        // then
        int expected = 0;
        assertEquals(expected, result);
    }
}