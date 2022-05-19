package Controllers;

import Entities.Item;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuController {


    private ArrayList<Item> items;
    private Scanner scanner = new Scanner(System.in);
    private int currentMenu;


    public MenuController(ArrayList<Item> items) {
        this.currentMenu = 1; // default menu is menu 1.
        this.items = items;
    }

//    public void loadMenu(int menuNumber) {
//     this might cycle through all the menuItems and put them into different Lists of menu's.
//     at the moment the menuNumber(to which menu it belongs(summer,winter etc.)) is embedded in each menuItem.
//    Menu menu = new Menu(1);
//    }


    public ArrayList<Item> printMenu() {
        ArrayList<Item> toPrint = new ArrayList<>();
        for (Item Item : items) {
            if (Item.getMenuNumber() == this.currentMenu) {
                toPrint.add(Item);
            }
        }
        return toPrint;
    }

    public ArrayList<Item> printCourse(String course) {
        ArrayList<Item> toPrint = new ArrayList<>();
        for (Item item : items) {
            if (item.getCourseType().equalsIgnoreCase(course)) {
                toPrint.add(item);
            }
        }
        return toPrint;
    }
}
