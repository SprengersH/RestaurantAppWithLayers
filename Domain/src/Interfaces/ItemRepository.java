package Interfaces;

import Entities.Item;

import java.util.ArrayList;

public interface ItemRepository {

    ArrayList<Item> getAllItems();

    ArrayList<Item> selectMenu(int menunumber);
}
