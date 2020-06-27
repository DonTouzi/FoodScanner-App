package com.univ_amu.food_scanner.data.network;

import com.univ_amu.food_scanner.data.Food;
import com.univ_amu.food_scanner.data.Quantity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Converters {
    public static Food getFood(NetworkFood food) {
        Food Food = new Food(food.code, food.name, food.brands, food.nutriscore, new Date());
        return Food;
    }

    public static List<Quantity> getQuantities(NetworkFood food) {
        List<Quantity> quantities = new ArrayList<>();
        for (NetworkFood.NetworkQuantity quantity : food.quantities) {
            quantities.add(new Quantity(food.code, quantity.name, quantity.rank, quantity.level, quantity.quantity, quantity.unit));
        }
        return quantities;
    }
}