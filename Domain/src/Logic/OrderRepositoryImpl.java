package Logic;

import Logic.Interfaces.ItemRepository;
import Logic.Interfaces.OrderRepository;

import java.sql.SQLException;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {

    @Override
    public void insertOrder(String orderID, double price, int tableNumber, int activeOrNot) {

    }

    @Override
    public void insertOrderToProduct(OrderRepository order, List<ItemRepository> orderedMenuMenuItems) {

    }

    @Override
    public String getOrderidFromTablenumber(int tableNumber) throws SQLException {
        return null;
    }

    @Override
    public List<Item> retrieveItemsFromOrder(String orderID) {
        return null;
    }

    @Override
    public List<Integer> getProductIDs(String orderID) {
        return null;
    }

    @Override
    public void setAvailable(int tableNumber) {

    }
}
