package Controllers;

import Entities.Item;
import Entities.Order;
import Interfaces.DiscountRules;
import Interfaces.ItemRepository;
import Interfaces.OrderRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BusinessController {

    private OrderRepository orderRepo;
    private List<Order> orders;
    private List<Item> items;
    private int currentMenu;

    public BusinessController(ItemRepository itemRepo, OrderRepository orderRepo) {
        this.currentMenu = 1; // default menu is menu 1.
        this.orderRepo = orderRepo;
        this.orders = orderRepo.getOrders();
        this.items = itemRepo.getAllItems();
    }

    public void setCurrentMenu(int currentMenu) {
        this.currentMenu = currentMenu;
    }

    public int getCurrentMenu() {
        return currentMenu;
    }

    public ArrayList<Item> printCurrentMenu() {
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

    public List<Item> getAllItems() {
        return items;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addItemsToOrder(List<Item> itemsToAdd, int tableNumber) {
        // this should just return the entire Order-object
        String orderID = orderRepo.getOrderidFromTablenumber(tableNumber);
        Order order;
        if (orderID.equals("")) {
            order = new Order(tableNumber, itemsToAdd);
            addOrderToDatabase(order);
        } else {
            order = new Order(tableNumber, orderID, itemsToAdd);
        }
        linkOrderToProduct(order, itemsToAdd);
        itemsToAdd.clear();
    }

    public void addOrderToDatabase(Order order) {
        orderRepo.insertOrder(order.getOrderID(), order.getPrice(), order.getTableNumber(), order.getActive(), order.getDate());
    }

    public void linkOrderToProduct(Order order, List<Item> orderedMenuMenuItems) {
        orderRepo.insertOrderToProduct(order, orderedMenuMenuItems);
    }

    public List<Item> retrieveItemList(String orderID) {
        return orderRepo.retrieveItemsFromOrder(orderID);
    }

    public Order getOrderFromTablenumber(int tableToCheckout) {
        String orderID = retrieveOrderID(tableToCheckout);
        List<Item> menuItemsToBill = getItemListFromOrderID(orderID);
        Order order = new Order(tableToCheckout, orderID, menuItemsToBill);
        return order;
    }


    public List<Item> getItemListFromOrderID(String orderID) {
        return orderRepo.retrieveItemsFromOrder(orderID);
    }

    public String retrieveOrderID(int tableNumber) {
        String id;
        id = orderRepo.getOrderidFromTablenumber(tableNumber);
        return id;
    }

    public void setAvailable(int tableNumber) {
        orderRepo.setAvailable(tableNumber);
        // todo no sout's outside of ui, this should return one or more lists to the ui and the ui should print the statements.
        System.out.println("Table " + tableNumber + " is set available");
    }

    public void checkForDiscounts(Order order) {
        List<DiscountRules> discountRules = new ArrayList<>();
        discountRules.add(new BeerDiscount(order));
        discountRules.add(new CombiDiscount(order));
        order.getPriceAfterDiscountsBetter(discountRules);
        updateOrder(order);
    }

    private void updateOrder(Order order) {
        orderRepo.updateOrder(order);
    }

    public void getSales(String timePeriod) {
        if (timePeriod.equalsIgnoreCase("MONTH")) {
            LocalDate dt = LocalDate.now();
            orderRepo.getSales(LocalDate.from(dt.getMonth()));
        }
    }
}
