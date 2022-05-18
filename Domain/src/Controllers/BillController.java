package Controllers;


import Entities.Bill;
import Entities.Item;

import java.util.List;
import java.util.Scanner;

public class BillController {

    private Scanner scanner = new Scanner(System.in);
    private DbController dbController;

    public BillController(DbController dbController) {
        this.dbController = dbController;
    }

    public void billMode() {
        int tableNumber = getInput();
        String orderID = retrieveOrderID(tableNumber);

        List<Item> menuItemsToBill = getItemListFromOrderID(orderID);
        billCreator(tableNumber, orderID, menuItemsToBill);
        System.out.println("Checkout 'Y' to clear table and return to main menu \n 'N' returns to main menu without checking out");

        scanner.nextLine();
        String input = scanner.nextLine().toUpperCase();

        if (input.equalsIgnoreCase("Y")) {
            //dbController.setAvailable(tableNumber);
            //Table table = new Table(tableNumber);
            // todo table.setAvailable(tableNumber);
            // todo this is where discounts should happen.
            // bill could be sent to the database here...
            //Main.setTable(tableNumber);
            //Main.run();
        } else if (input.equalsIgnoreCase("N")) {
           // Main.run();
        } else {
            System.out.println("Give decent input you animal");
        }
    }

    private void billCreator(int tableNumber, String orderID, List<Item> menuItemsToBill) {
        Bill bill = new Bill(tableNumber, orderID, menuItemsToBill);
        System.out.println(bill);
    }

    private List<Item> getItemListFromOrderID(String orderID) {
       return null; //dbController.retrieveItemList(orderID);
    }

    public Integer getInput() {
        int input = scanner.nextInt();
        return input;
    }

    public String retrieveOrderID(int tableNumber) {
        return null; //dbController.retrieveOrderID(tableNumber);
    }


}
