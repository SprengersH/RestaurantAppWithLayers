package Logic;

import Logic.Interfaces.ItemRepository;
import Logic.Item;

import java.util.ArrayList;

public class ItemDTO {

    private  int menuNumber;
    private  int menuItemID;
    private  String courseType;
    private  String description;
    private  String name;
    private  double price;


    public ItemDTO(int menuItemID, int menuNumber, String courseType, String name, String description, double price) {
        this.menuItemID = menuItemID;
        this.menuNumber = menuNumber;
        this.courseType = courseType;
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
