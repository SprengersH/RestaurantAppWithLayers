package Interfaces;


import Entities.Item;

import java.util.List;

public interface DiscountRules {

     double applyDiscount();

     double applyDiscountBetter(List<Item> items);

}
