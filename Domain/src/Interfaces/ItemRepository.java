package Interfaces;

import Entities.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemRepository {

    ArrayList<Item> getItems();

    ArrayList<Item> selectMenu(int menunumber);
}
