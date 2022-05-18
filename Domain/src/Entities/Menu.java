package Entities;

import Entities.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

// at the moment this class doesnt do anything yet as the menuNumber is embedded in each menuItem.

public class Menu {

    private int menuNumber;
    private List<Item> menuItemList;
	private Collection<Item> itemList;

    public Menu(int menuNumber) {

        this.menuNumber = menuNumber;
        this.menuItemList = new ArrayList<>();
    }
}
