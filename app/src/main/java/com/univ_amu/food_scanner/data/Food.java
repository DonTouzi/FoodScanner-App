package com.univ_amu.food_scanner.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity
public class Food {
    public Food(String code, String name, String brands, String nutriscore, Date date) {
        this.code = code;
        this.name = name;
        this.brands = brands;
        this.nutriscore = nutriscore;
        this.date = date;
    }

    @PrimaryKey
    @NonNull
    public String code;

    @NonNull
    public String name;

    @NonNull
    public String brands;

    @NonNull
    public String nutriscore;

    @NonNull
    public Date date;
}