package Logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderController {


    private Scanner scanner = new Scanner(System.in);
    private MenuController menuController;
    private List<Item> items;

    private List<Item> itemsToAdd;
    //UI ui = new UI();
    OrderData database = new OrderData();

    public OrderController(MenuController menuController) {

        this.menuController = menuController;
        this.itemsToAdd = new ArrayList();
        ArrayList<Item> data = new Item().GetItems();
        for (Item itemdata : data) {
            this.items.add(new Item
                    (itemdata.getMenuItemID(),
                            itemdata.getMenuNumber(),
                            itemdata.getCourseType(),
                            itemdata.getName(),
                            itemdata.getDescription(),
                            itemdata.getPrice()));
        }
    }

    public void test() {
        database.insertOrder("test", 12.00, 1, 1);
    }

    public void orderMode() {
        //myRestaurant.printAllTables();
        System.out.println("Select table number:");
        int tableNumber = scanner.nextInt();
        // todo validation that selected table is available.
        // myRestaurant.setUnavailable(tableNumber);
        courseSelector();
        itemSelector(tableNumber);
    }

    private void courseSelector() {
        // todo remove all printstatements to the UI then delete menucontroller from ordercontroller.
        //ui.showCourses();
        int input = scanner.nextInt();
        switch (input) {
            case (1) -> {
                //ui.showDrinksPage();
                menuController.printCourse("DRINKS");

            }
            case (2) -> {
                //ui.showMainCourses();
                menuController.printCourse("MAIN COURSE");

            }
            case (3) -> {
                //ui.showSideDishes();
                menuController.printCourse("SIDE DISH");

            }
            case (4) -> {
                System.out.println("Going back to the main page:");
                //Main.run();
            }

            default -> {
                System.out.println("Please choose a valid option.");
                courseSelector();
            }

        }
    }

    private void itemSelector(int tableNumber) {

        int input = scanner.nextInt();
        if (input < 1) {

            if (itemsToAdd.size() < 1) {
                System.out.println("No items selected, please enter at least 1 item.");
            } else {
                addToOrder(itemsToAdd, tableNumber);
            }

        } else {
            for (Item menuItem : items) {
                if (menuItem.getMenuItemID() == input) {
                    itemsToAdd.add(menuItem);
                }
            }
            itemSelector(tableNumber);
        }
    }


    private void addToOrder(List<Item> itemsToAdd, int tableNumber) {
        Order order = new Order(tableNumber, itemsToAdd);

        addOrderToDatabase(order);
        itemsToAdd.clear();
    }

    private void addOrderToDatabase(Order order) {
        DbController dbc = new DbController();
        dbc.insertOrder(order);
    }


}

