package com.univ_amu.food_scanner.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.univ_amu.food_scanner.data.Food;
import com.univ_amu.food_scanner.data.Quantity;
import java.util.List;

@androidx.room.Dao
public interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFood(Food food, List<Quantity> quantity);

    @Query("SELECT * FROM Food ORDER BY date DESC")
    LiveData<List<Food>> getFoods();

    @Query("SELECT COUNT() FROM Food")
    LiveData<Integer> getFoodCount();

    @Query("SELECT * FROM Food WHERE code = :code")
    LiveData<Food> getFood(String code);

    @Query("SELECT * FROM Quantity WHERE foodCode = :foodCode ORDER BY rank ASC")
    LiveData<List<Quantity>> getQuantities(String foodCode);
}