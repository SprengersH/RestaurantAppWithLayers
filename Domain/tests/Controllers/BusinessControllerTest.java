package Controllers;

import Entities.Item;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BusinessControllerTest {

    private final BusinessController underTest = new BusinessController(
            new MockItemDAL(),
            new MockOrderDAL()
    );

    @Test
    void checkForDiscounts() {


    }

    @Test
    void shouldSetCurrentMenuToTwo() {
        // given
        int expected = 2;
        // when
        underTest.setCurrentMenu(2);
        // then
        assertEquals(expected, underTest.getCurrentMenu());
    }


    @Test
    void printCurrentMenu() {
        // given
        ArrayList<Item> toPrint = underTest.printCurrentMenu();
        int expected = 3;
        int actual = toPrint.size();
        // then
        assertEquals(actual, expected);
    }

    @Test
    void getAllItems() {

    }

    @Test
    void getOrders() {
    }
}