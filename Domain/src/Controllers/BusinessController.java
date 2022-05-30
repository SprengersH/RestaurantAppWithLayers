package Controllers;

import Entities.BeerDiscount;
import Entities.CombiDiscount;
import Entities.Item;
import Entities.Order;
import Interfaces.DiscountRules;
import Interfaces.ItemRepository;
import Interfaces.OrderRepository;

import java.util.ArrayList;
import java.util.List;

public class BusinessController {

    private OrderRepository orderRepo;
    private ItemRepository itemRepo;

    private List<Order> orders;
    private List<Item> items;
    private int currentMenu;

    public BusinessController(ItemRepository itemRepo, OrderRepository orderRepo) {
        this.currentMenu = 1; // default menu is menu 1.
        this.orderRepo = orderRepo;
        this.orders = getOrders();
        this.itemRepo = itemRepo;
        this.items = getAllItems();

    }

    public void setCurrentMenu(int currentMenu) {
        this.currentMenu = currentMenu;
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
        items = itemRepo.getAllItems();
        return items;
    }
    public List<Order> getOrders() {
        orders = orderRepo.getOrders();
        return orders;
    }

    public void addItemsToOrder(List<Item> itemsToAdd, int tableNumber) {
        Order order = new Order(tableNumber, itemsToAdd);
        addOrderToDatabase(order);
        linkOrderToProduct(order, itemsToAdd);
        itemsToAdd.clear();
    }

    public void addOrderToDatabase(Order order) {
        orderRepo.insertOrder(order.getOrderID(), order.getPrice(), order.getTableNumber(), order.getActive());
    }

    public void linkOrderToProduct(Order order, List<Item> orderedMenuMenuItems){
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
        order = order.getDiscounts(discountRules);
        updateOrder(order);
    }

    private void updateOrder(Order order) {
        orderRepo.updateOrder(order);
    }
}
