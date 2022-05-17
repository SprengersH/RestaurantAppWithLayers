package Logic.Interfaces;

import java.util.ArrayList;

public interface DBMenuItemsInterfacer {

    ArrayList<ItemDao> selectAllMenuItems();

    ArrayList<ItemDao> selectMenu(int menunumber);
}
