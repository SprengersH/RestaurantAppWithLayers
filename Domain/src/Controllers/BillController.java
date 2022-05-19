package Controllers;


import Entities.Bill;
import Entities.Item;
import Entities.Order;
import Interfaces.BillRepository;
import Interfaces.ItemRepository;
import Interfaces.OrderRepository;

import java.util.List;
import java.util.Scanner;

public class BillController {

    private OrderRepository orderRepo;
    private ItemRepository itemRepo;
    private BillRepository billRepo;
    private List<Order> orders;
    private List<Item> items;
    private Scanner scanner = new Scanner(System.in);

    public BillController(ItemRepository itemRepo, OrderRepository orderRepo, BillRepository billRepo) {
        this.orderRepo = orderRepo;
        this.itemRepo = itemRepo;
        this.billRepo = billRepo;
    }

    public List<Item> getAllItems() {
        items = itemRepo.getAllItems();
        return items;
    }
    public List<Order> getOrders() {
        orders = orderRepo.getOrders();
        return orders;
    }


    public Bill billing(int tableToCheckout) {
        String orderID = retrieveOrderID(tableToCheckout);
        List<Item> menuItemsToBill = getItemListFromOrderID(orderID);
        return billCreator(tableToCheckout, orderID, menuItemsToBill);
    }

    private Bill billCreator(int tableNumber, String orderID, List<Item> menuItemsToBill) {
        Bill bill = new Bill(tableNumber, orderID, menuItemsToBill);
        return bill;
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


    public void insertBill(Bill bill) {
        billRepo.insertBill(bill);
    }
}
