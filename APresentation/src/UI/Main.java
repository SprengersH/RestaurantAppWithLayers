package UI;

import Controllers.BillController;
import Controllers.MenuController;
import Controllers.OrderController;
import Database.BillDAL;
import Database.ItemDAL;
import Database.OrderDAL;
import Entities.Bill;
import Entities.Item;
import Entities.Restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Restaurant myRestaurant = new Restaurant();
    private static final Scanner scanner = new Scanner(System.in);
    private static final UI ui = new UI();
    private static final MenuController menuController = new MenuController(new ItemDAL());
    private static final OrderController orderController = new OrderController(new ItemDAL(), new OrderDAL());
    private static final BillController billController = new BillController(new ItemDAL(), new OrderDAL(), new BillDAL());


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
                System.out.println("*   Selected Order                    *");
                orderMode();
            }
            case (2) -> {
                System.out.println("*   Selected Menu                     *");
                menuMode();
            }
            case (3) -> {
                System.out.println("*   Displaying Table Status:          *");
                tableMode();
            }
            case (4) -> {
                System.out.println("*   Selected Checkout                 *");
                billingMode();
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



    private static void orderMode() {
        System.out.println("Select table number:");
        int tableNumber = scanner.nextInt();
        CourseSelector();
        List<Item> toAdd;
        toAdd = itemSelector();
        orderController.addItemsToOrder(toAdd, tableNumber);
        myRestaurant.setUnavailable(tableNumber);
        run();
    }

    public static void menuMode() {
        ui.showMenuPage();
        int input = scanner.nextInt();
        switch (input) {
            case (1) -> {
                // TODO this should show the contents of the current menu with courses etc for design, maybe when i've got extra time and everything works
                System.out.println("selected 1");
                System.out.println("this doesn't do anything yet");
                menuMode();
            }
            case (2) -> {
                System.out.println("Displaying current menu:");
                Printer.print(menuController.printCurrentMenu());
                menuMode();
            }
            case (3) -> {
                System.out.println("Changing menu's");
                changeMenu();
                menuMode();
            }
            case (4) -> {
                System.out.println("Going back to the main page:");
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
                List<Item> drinks = menuController.printCourse("DRINKS");
                Printer.print(drinks);
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

    private static List<Item> itemSelector() {

        List<Item> selectedItems = new ArrayList<>();
        boolean keepGoing = true;
        while (keepGoing) { // keep asking input
            System.out.println("Select item:");

            int chosenItem = scanner.nextInt();

            if (chosenItem < 1) { // when <1 is entered check if the list has at least 1 item to add to the order.

                if (selectedItems.size() < 1) { // if list is <1 there are no selected items yet, keep going.
                    System.out.println("No items selected, please enter at least 1 item.");
                } else {
                    keepGoing = false; // if list is >0 when "0" is entered, stop keepGoing.
                }
            } else { // if input is >=1 search all items and check that item exists.
                boolean containsID = menuController.getAllItems().stream().anyMatch(item -> chosenItem == (item.getMenuItemID()));
                // this now uses a list from in-memory repository instead of database
                if (!containsID) { // if it doesn't exist, start over, if it does add it the list.
                    System.out.println("No such item in the menu, please try again");
                }
                for (Item menuItem : menuController.getAllItems()) { // check all items and add the selection of the user to the list
                    if (menuItem.getMenuItemID() == chosenItem) {
                        selectedItems.add(menuItem);
                    }
                }
            }
        }
        return selectedItems;
    }

    private static void billingMode() {
        System.out.println("Enter table number to checkout:.....");
        int tableToCheckout = scanner.nextInt();
        Bill bill = billController.billing(tableToCheckout);
        System.out.println("Checking out: \n" + bill + "\n Type 'Y' to clear table and return to main menu \n 'N' returns to main menu without checking out");
        scanner.nextLine();
        String input = scanner.nextLine().toUpperCase();

        if (input.equalsIgnoreCase("Y")) {
            // todo this is where discounts should happen.
            billController.setAvailable(tableToCheckout); // in db
            setTableAvailable(tableToCheckout);           // in memory
            // bill could be sent to the database here...
            billController.insertBill(bill);
            run();
        }
        if (input.equalsIgnoreCase("N")) {
            run();
        } else {
            System.out.println("Give decent input you animal");
            billingMode();
        }
    }

    public String retrieveOrderID(int tableNumber) {
        return orderController.retrieveOrderID(tableNumber);
    }

    private static void tableMode() {
        myRestaurant.printAllTables();
        run();
    }

    public static void setTableAvailable(int tableNumber) {
        myRestaurant.setAvailable(tableNumber);
    }

    private static void changeMenu() {
        int input;
        System.out.println("Select new menu:");
        input = scanner.nextInt();
        menuController.setCurrentMenu(input);
        System.out.println("Changed to menu " + input);
    }

}

