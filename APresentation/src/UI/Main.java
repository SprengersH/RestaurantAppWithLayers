package UI;

import Controllers.BillController;
import Controllers.DbController;
import Controllers.MenuController;
import Controllers.OrderController;
import DTO.ItemDTO;
import Database.ItemDAL;
import Entities.Restaurant;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // had to make all of these static in order to be able to go back to the main menu (option 4 from menuController)
    // if i didn't, a new restaurant object would be recreated everytime I went back to the main page (run()) and all the options would be reset.

    static DbController dbController = new DbController();
    static Restaurant myRestaurant = new Restaurant(10);
    static MenuController menuController = new MenuController();
    static OrderController orderController = new OrderController(menuController);
    static BillController billController = new BillController(dbController);


    public static void main(String[] args) {
        ArrayList<ItemDTO> itemList = new ItemDAL().getItems(); // cant put this in domain bc of dependency...
        DbController dbc = new DbController(itemList);
        dbController.test(itemList); // now i have a list of ItemDTO's in the domain layer. Should this controller be in the presentation layer??
        run();
    }

    public static void run() {

        Scanner scanner = new Scanner(System.in);
        UI ui = new UI();
        ui.showMainMenu();

        int input = scanner.nextInt();
        switch (input) {
            case (1) -> {
                // this should show available tables, the option to select the current table for a customer and show the current menu and allow ordering of menuItems.
                System.out.println("selected order");
                // todo don't go to the controller until you have all the info, get input first and activate the controller with input.
                orderController.orderMode();
            }
            case (2) -> {
                // this should show the available menu's and allow you to change menu's.
                System.out.println("selected menu");
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
                billController.billMode();

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

    public static void setTable(int tableNumber) {
        myRestaurant.setAvailable(tableNumber);
    }
}
