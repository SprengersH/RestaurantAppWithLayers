package Logic.Interfaces;


import Logic.Order;

import java.sql.SQLException;
import java.util.List;

public interface DBOrdersInterfacer {

    void insertOrder(Order order);

    void insertOrderToProduct(Order order, List<ItemDao> orderedMenuMenuItems);

    String getOrderidFromTablenumber(int tableNumber) throws SQLException;

    List<ItemDao> retrieveItemsFromOrder(String orderID);

    List<Integer> getProductIDs(String orderID);

    void setAvailable(int tableNumber);
}
