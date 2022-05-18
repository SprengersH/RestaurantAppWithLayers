package Entities;

import java.util.List;
import java.util.UUID;

public class Customer {

    private String id;
    private String name;
    private String contactNumber;
    private List<Order> orders;

    public Customer(String id, String name, String contactNumber, List<Order> orders) {
        this.id = UUID.randomUUID().toString();;
        this.name = name;
        this.contactNumber = contactNumber;
        this.orders = orders;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContactNumber() {
        return contactNumber;
    }


    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", orders=" + orders +
                '}';
    }
}
