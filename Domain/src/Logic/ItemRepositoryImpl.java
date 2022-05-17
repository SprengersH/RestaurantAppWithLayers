package Logic;

import Logic.Interfaces.ItemRepository;
import Logic.Item;

import java.util.ArrayList;

public class ItemRepositoryImpl{

    ArrayList<ItemRepository> itemList;

    public ItemRepositoryImpl(ArrayList<Item> itemList) {
        ArrayList<Item> itemList = new ItemDaoImpl().getItems();
    }
}
