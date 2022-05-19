package UI;

import Controllers.DbController;
import Controllers.MenuController;
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

    private static List<Item> itemsToAdd = new ArrayList<>();

    private static DbController dbc = new DbController(new ItemDAL(), new OrderDAL());
    private static MenuController menuController = new MenuController(dbc.getAllItems());

    // static BillController billController = new BillController(dbController);


    public static void main(String[] args) {
        run();
    }

    public static void run() {

        ui.showMainMenu();

        int input = scanner.nextInt();
        switch (input) {
            case (1) -> {
                // this should show available tables, the option to select the current table for a customer and show the current menu and allow ordering of menuItems.
                System.out.println("selected order");
                // todo don't go to the controller until you have all the info, get input first and activate the controller with input.
                System.out.println("Select table number:");
                int tableNumber = scanner.nextInt();

                CourseSelector();
                itemSelector(tableNumber);
                myRestaurant.setUnavailable(tableNumber);
                run();
            }
            case (2) -> {
                // this should show the available menu's and allow you to change menu's.
                System.out.println("selected menu");
                ui.showMenuPage();

                menuController.menuOptions();
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

    private static void itemSelector(int tableNumber) {
        System.out.println("Select item:");
        int input = scanner.nextInt();
        ArrayList<Item> items = new ArrayList<>();

        if (input < 0) {

            if (itemsToAdd.size() < 1) {
                System.out.println("No items selected, please enter at least 1 item.");
                ui.showCourses();
                itemSelector(tableNumber);
            } else {
                addToOrder(itemsToAdd, tableNumber);  // SOLID srp should this be selecting AND adding???
            }

        } else {

            boolean containsID = dbc.getAllItems().stream().anyMatch(item -> input == (item.getMenuItemID())); // using a stream to see if the input exists in the menu
            if (!containsID) {
                System.out.println("No such item in the menu, please try again");
                itemSelector(tableNumber);
            }

            for (Item menuItem : dbc.getAllItems()) {
                if (menuItem.getMenuItemID() == input) {
                    itemsToAdd.add(menuItem);
                }
            }
            itemSelector(tableNumber);
        }
    }

    private static void addToOrder(List<Item> itemsToAdd, int tableNumber) {
        Order order = new Order(tableNumber, itemsToAdd);
        addOrderToDatabase(order);
        itemsToAdd.clear();
    }

    private static void addOrderToDatabase(Order order) {
        dbc.insertOrder(order.getOrderID(), order.getPrice(), order.getTableNumber(), order.getActive());
    }

    public static void setTable(int tableNumber) {
        myRestaurant.setAvailable(tableNumber);
    }

}

