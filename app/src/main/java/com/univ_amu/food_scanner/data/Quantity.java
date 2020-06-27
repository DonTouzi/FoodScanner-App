package com.univ_amu.food_scanner.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(primaryKeys = { "foodCode", "name" },
        foreignKeys = {
                @ForeignKey(entity = Food.class,
                        parentColumns = "code",
                        childColumns = "foodCode",
                        onDelete = ForeignKey.CASCADE,
                        onUpdate = ForeignKey.CASCADE)})

public class Quantity {
    public Quantity(String foodCode, String name, int rank, int level, double value, String unit) {
        this.foodCode = foodCode;
        this.name = name;
        this.rank = rank;
        this.level = level;
        this.value = value;
        this.unit = unit;
    }

    @NonNull
    public String foodCode;

    @NonNull
    public String name;
    public int rank;
    public int level;
    public double value;

    @NonNull
    public String unit;
}