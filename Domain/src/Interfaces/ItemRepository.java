package Interfaces;

import Entities.Item;

import java.util.ArrayList;

public interface ItemRepository {

    ArrayList<Item> getItems();

    ArrayList<Item> selectMenu(int menunumber);
}
