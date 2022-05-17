package Logic.Interfaces;

import Logic.Item;
import Logic.ItemDTO;

import java.util.ArrayList;

public interface ItemRepository {

    ArrayList<ItemDTO> getItems();

    ArrayList<ItemDTO> selectMenu(int menunumber);
}
