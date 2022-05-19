package UI;

import Controllers.DbController;
import Controllers.MenuController;
import Controllers.OrderController;
import Database.ItemDAL;
import Database.OrderDAL;
import Entities.Item;
import Entities.Order;
import Entities.Restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Restaurant myRestaurant = new Restaurant(10);
    private static Scanner scanner = new Scanner(System.in);
    private static UI ui = new UI();
    private static DbController dbc = new DbController(new ItemDAL(), new OrderDAL());
    private static MenuController menuController = new MenuController(dbc.getAllItems());
    private static OrderController orderController = new OrderController(dbc, dbc.getOrders());
    // static BillController billController = new BillController(dbController);


    public static void main(String[] args) {
        run();
    }

    public static void run() {

        ui.showMainMenu();
        MainMenuSelector();

    }

    private static void MainMenuSelector() {
        int input = scanner.nextInt();
        switch (input) {
            case (1) -> {
                // this should show available tables, the option to select the current table for a customer and show the current menu and allow ordering of menuItems.
                System.out.println("selected order");
                System.out.println("Select table number:");
                int tableNumber = scanner.nextInt();
                CourseSelector();
                List<Item> toAdd;
                toAdd = itemSelector(tableNumber);
                orderController.addToOrder(toAdd, tableNumber);
                myRestaurant.setUnavailable(tableNumber);
                run();
            }
            case (2) -> {
                // this should show the available menu's and allow you to change menu's.
                System.out.println("selected menu");
                ui.showMenuPage();
                menuOptions();
            }
            case (3) -> {
                // this should display all tables including their availability.
                System.out.println("*   Displaying Table Status:                 *");
                myRestaurant.printAllTables();
                run();
            }
            case (4) -> {
                System.out.println("selected checkout");
                System.out.println("Enter tablenumber to checkout:.....");
                //billController.billMode();

            }
            // TODO this should ask for a time period and show you the sales in that period.
            case (5) -> {
                System.out.println("selected sales records");
                System.out.println("Nothing here yet, go back");
                run();
            }

            default -> System.out.println("Please choose a valid option.");
        }
    }

    public static void menuOptions() {
        // todo printstatements done by ui plz
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
                Printer.print(menuController.printMenu()); // hey this works.
                menuOptions();
            }
            case (3) -> {
                // this allows you to change between menu's.
                System.out.println("Changing menu's");
                System.out.println("Select new menu:");
                //this.currentMenu = scanner.nextInt();
                menuOptions();
            }
            case (4) -> {
                System.out.println("Going back to the main page:");
                //Main.run();
            }

            default -> System.out.println("Please choose a valid option.");
        }

    }

    private static void CourseSelector() {
        ui.showCourses();
        int course = scanner.nextInt();
        switch (course) {
            case (1) -> {
                ui.showDrinksPage();
                menuController.printCourse("DRINKS");
            }
            case (2) -> {
                ui.showMainCourses();
                menuController.printCourse("MAIN COURSE");
            }
            case (3) -> {
                ui.showSideDishes();
                menuController.printCourse("SIDE DISH");
            }
            case (4) -> {
                System.out.println("Going back to the main page:");
                run();
            }
            default -> {
                System.out.println("Please choose a valid option.");
                CourseSelector();
            }
        }
    }

    private static List<Item> itemSelector(int tableNumber) {

        List<Item> itemsToAdd = new ArrayList<>();
        boolean keepGoing = true;
        while (keepGoing) {
            System.out.println("Select item:");

            int input = scanner.nextInt();
            if (input < 1) {

                if (itemsToAdd.size() < 1) {
                    System.out.println("No items selected, please enter at least 1 item.");
                } else {
                    keepGoing = false;
                }
            } else {
                boolean containsID = dbc.getAllItems().stream().anyMatch(item -> input == (item.getMenuItemID()));
                // using a stream to see if the input exists in the menu but it uses the list from the DB. Should use list from in-memory repository?
                if (!containsID) {
                    System.out.println("No such item in the menu, please try again");
                    itemSelector(tableNumber);
                }
                for (Item menuItem : dbc.getAllItems()) { // should this logic happen in the domainlayer?
                    if (menuItem.getMenuItemID() == input) {
                        itemsToAdd.add(menuItem);
                    }
                }
            }
        }
        return itemsToAdd;
    }

    private static void addOrderToDatabase(Order order) {
        dbc.insertOrder(order.getOrderID(), order.getPrice(), order.getTableNumber(), order.getActive());
    }

    public static void setTable(int tableNumber) {
        myRestaurant.setAvailable(tableNumber);
    }

}

