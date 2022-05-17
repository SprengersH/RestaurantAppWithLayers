package Logic.Interfaces;

import Logic.Item;

import java.util.ArrayList;

public interface ItemRepository {
    ArrayList<Item> GetItems();

    ArrayList<Item> selectMenu(int menunumber);
}
