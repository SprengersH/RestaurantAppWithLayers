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


    //public void loadMenu(int menuNumber) {
    // this might cycle through all the menuItems and put them into different Lists of menu's.
    // at the moment the menuNumber(to which menu it belongs(summer,winter etc.)) is embedded in each menuItem.
    //Menu menu = new Menu(1);
    //}



    public ArrayList<Item> printMenu() {
        // todo Single responsibility, should this be done by the ui???
        ArrayList<Item> toAdd = new ArrayList<>();
        for (Item menuItem : items) {
            if (menuItem.getMenuNumber() == this.currentMenu) {
                toAdd.add(menuItem);
            }
        } return toAdd;
            }

    public void printCourse(String course) {
// todo Single responsibility, should this be done by the ui???
        for (Item item : items) {
            if (item.getCourseType().equalsIgnoreCase(course)) {
                System.out.println(item);
            }
        }
    }
}
