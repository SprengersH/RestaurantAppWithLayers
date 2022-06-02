package Controllers;

import Entities.Item;
import Interfaces.ItemRepository;

import java.util.ArrayList;


public class MockItemDAL implements ItemRepository {
    @Override
    public ArrayList<Item> getAllItems() {
        ArrayList<Item> items = new ArrayList<>();
        Item item1 = new Item(1, 1, "DRINKS", "Bier", "testBier", 2.0);
        Item item2 = new Item(1, 1, "MAIN COURSE", "Snitzel", "testSnitzel", 2.0);
        Item item3 = new Item(1, 1, "SIDE DISH", "Friet", "testFriet", 2.0);
        items.add(item1);
        items.add(item2);
        items.add(item3);
        return items;
    }

    @Override
    public ArrayList<Item> selectMenu(int menunumber) {
        return null;
    }
}
