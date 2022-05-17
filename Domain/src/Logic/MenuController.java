package Logic;

import Database.ItemDaoImpl;
import Database.ItemRepository;
import Database.ItemsDAL;
import Logic.Interfaces.ItemRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuController {


    private ItemRepository itemRepository;
    private List<Item> items = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private int currentMenu;


    public MenuController(ArrayList<ItemRepository> data) {

        this.currentMenu = 1; // default menu is menu 1.
        // Dependency inversion done below????
        data = new ArrayList<>();
    }

    //public void loadMenu(int menuNumber) {
    // this might cycle through all the menuItems and put them into different Lists of menu's.
    // at the moment the menuNumber(to which menu it belongs(summer,winter etc.)) is embedded in each menuItem.
    //Menu menu = new Menu(1);
    //}

    public void menuOptions() {
        // todo printstatements done by ui plz
        // ui.showMenuPage();
        int input = scanner.nextInt();
        switch (input) {
            case (1) -> {
                // TODO this should show the contents of the current menu with courses etc.
                System.out.println("selected 1");
                System.out.println("this doesn't do anything yet");
                menuOptions();
            }
            case (2) -> {
                // this now shows the current selected menu. 1 by default.
                System.out.println("Displaying current menu:");
                printMenu();
                menuOptions();
            }
            case (3) -> {
                // this allows you to change between menu's.
                System.out.println("Changing menu's");
                System.out.println("Select new menu:");
                this.currentMenu = scanner.nextInt();
                menuOptions();
            }
            case (4) -> {
                System.out.println("Going back to the main page:");
                //Main.run();
            }

            default -> System.out.println("Please choose a valid option.");
        }

    }

    public void printMenu() {
        // todo Single responsibility, should this be done by the ui???
        for (Item menuItem : items) {
            if (menuItem.getMenuNumber() == this.currentMenu) {
                System.out.println(menuItem);
            }
        }
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
