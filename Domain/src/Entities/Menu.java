package Entities;

import Entities.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

// at the moment this class doesnt do anything yet as the menuNumber is embedded in each menuItem.

public class Menu {

    private int menuNumber;
    private ArrayList<Item> menuItemList;

    public Menu(ArrayList<Item> menuItemList) {

        this.menuNumber = 1;
        this.menuItemList = new ArrayList<>();
    }
}
