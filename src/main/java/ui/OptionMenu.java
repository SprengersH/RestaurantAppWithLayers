package ui;

import domain.Controllers.BusinessController;
import domain.Entities.Item;
import domain.Entities.Order;
import domain.Entities.Restaurant;
import infrastructure.Database.ItemDAL;
import infrastructure.Database.OrderDAL;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OptionMenu {

    private final Restaurant myRestaurant;
    private final Scanner scanner;
    private final UI ui;
    private final BusinessController businessController = new BusinessController(new ItemDAL(), new OrderDAL());

    public OptionMenu(UI ui, Restaurant restaurant) {
        this.ui = ui;
        this.myRestaurant = restaurant;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        ui.showMainMenu();
        MainMenuSelector();

    }

    private void MainMenuSelector() {
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

    private void orderMode() {
        System.out.println("Select table number:");
        int tableNumber = scanner.nextInt();
        courseSelector();
        List<Item> toAdd;
        toAdd = itemSelector();
        businessController.addItemsToOrder(toAdd, tableNumber);
        myRestaurant.setUnavailable(tableNumber);
        run();
    }

    public void menuMode() {
        ui.showMenuPage();
        int input = scanner.nextInt();
        switch (input) {
            case (1) -> {
                System.out.println("selected 1");
                System.out.println("this doesn't do anything yet");
                menuMode();
            }
            case (2) -> {
                System.out.println("Displaying current menu:");
                Printer.print(businessController.printCurrentMenu());
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



    private void courseSelector() {
        ui.showCourses();
        int course = scanner.nextInt();
        switch (course) {
            case (1) -> {
                ui.showDrinksPage();
                List<Item> drinks = businessController.printCourse("DRINKS");
                Printer.print(drinks);
            }
            case (2) -> {
                ui.showMainCourses();
                List<Item> mainCourses = businessController.printCourse("MAIN COURSE");
                Printer.print(mainCourses);
            }
            case (3) -> {
                ui.showSideDishes();
                List<Item> sideDish = businessController.printCourse("SIDE DISH");
                Printer.print(sideDish);
            }
            case (4) -> {
                System.out.println("Going back to the main page:");
                run();
            }
            default -> {
                System.out.println("Please choose a valid option.");
                courseSelector();
            }
        }
    }

    private List<Item> itemSelector() {

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
                boolean containsID = businessController.getAllItems().stream().anyMatch(item -> chosenItem == (item.getMenuItemID()));
                // this now uses a list from in-memory repository instead of database
                if (!containsID) { // if it doesn't exist, start over, if it does add it the list.
                    System.out.println("No such item in the menu, please try again");
                }
                for (Item menuItem : businessController.getAllItems()) { // check all items and add the selection of the user to the list
                    if (menuItem.getMenuItemID() == chosenItem) {
                        selectedItems.add(menuItem);
                    }
                }
            }
        }
        return selectedItems;
    }

    private void billingMode() {
        System.out.println("Enter table number to checkout:.....");
        int tableToCheckout = scanner.nextInt();

        System.out.println("Checking out table: \n" + tableToCheckout + "\n Type 'Y' to clear table and return to main menu \n 'N' returns to main menu without checking out");
        scanner.nextLine();
        String input = scanner.nextLine().toUpperCase();

        if (input.equalsIgnoreCase("Y")) {
            Order order = businessController.getOrderFromTablenumber(tableToCheckout);
            System.out.println("Order before discounts:");
            System.out.println(order);
            businessController.checkForDiscounts(order);
            System.out.println("Order after discounts:");
            System.out.println(order);
            // todo print order nicely maybe before and after discount??
            businessController.setAvailable(tableToCheckout); // in db
            setTableAvailable(tableToCheckout);           // in memory

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
        return businessController.retrieveOrderID(tableNumber);
    }

    private void tableMode() {
        myRestaurant.printAllTables();
        run();
    }

    public void setTableAvailable(int tableNumber) {
        myRestaurant.setAvailable(tableNumber);
    }

    private void changeMenu() {
        int input;
        System.out.println("Select new menu:");
        input = scanner.nextInt();
        businessController.setCurrentMenu(input);
        System.out.println("Changed to menu " + input);
    }

}

