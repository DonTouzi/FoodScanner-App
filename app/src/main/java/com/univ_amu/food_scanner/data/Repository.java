package com.univ_amu.food_scanner.data;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.univ_amu.food_scanner.data.database.Dao;
import com.univ_amu.food_scanner.data.database.Database;
import com.univ_amu.food_scanner.data.network.Converters;
import com.univ_amu.food_scanner.data.network.Network;
import com.univ_amu.food_scanner.data.network.NetworkFood;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    private final Context context;
    private final ExecutorService executor;
    private Dao dao;

    public Repository(Context context) {
        this.context = context;
        this.dao = null;
        executor = Executors.newSingleThreadExecutor();
        this.addRandomFoodsIfEmpty();
    }

    private Dao dao() {
        if (dao==null) {
            Database database = Database.getInstance(context);
            this.dao = database.dao();
        }
        return this.dao;
    }

    public LiveData<List<Food>> getFoods() {
        return dao().getFoods();
    }

    public LiveData<Integer> getFoodCount() {
        return dao().getFoodCount();
    }

    public LiveData<Food> getFood(String foodCode) {
        return dao().getFood(foodCode);
    }

    public LiveData<List<Quantity>> getQuantities(String foodCode) {
        return dao().getQuantities(foodCode);
    }

    public void insertFood(Food food, List<Quantity> quantities) {
        executor.execute(()->dao().insertFood(food, quantities));
    }

    public void addRandomFoodsIfEmpty() {
        getFoodCount().observeForever(value->{
            if (value != 0) return;
            int foodCode = 10000;
            for (Date date : dates) {
                for (int i = 0; i < 2; i++) {
                    addRandomFood(date);
                    foodCode++;
                }
            }
        });
    }

    public void addRandomFood(Date date) {
        String foodCode = String.valueOf(random.nextInt(1000000));
        String name = names[random.nextInt(names.length)];
        String nutriscore = nutriscores[random.nextInt(nutriscores.length)];
        String brand = brands[random.nextInt(brands.length)];
        List<Quantity> quantities = generateRandomQuantities(foodCode);
        insertFood(new Food(foodCode, name, brand, nutriscore, date), quantities);
    }

    private List<Quantity> generateRandomQuantities(String foodCode) {
        return Arrays.asList(new Quantity(foodCode, "Ã‰nergie",0, 0,
                        random.nextInt(50000)/100.0, "kcal"),
                new Quantity(foodCode, "Glucides",1, 0,
                        random.nextInt(5000)/100.0, "g"),
                new Quantity(foodCode, "Sucre",2, 1,
                        random.nextInt(5000)/100.0, "g"),
                new Quantity(foodCode, "MatiÃ¨res grasses",3, 0,
                        random.nextInt(5000)/100.0, "g"),
                new Quantity(foodCode, "Acide gras saturÃ©s",4, 1,
                        random.nextInt(5000)/100.0, "g"));
    }

    public LiveData<Boolean> downloadFood(String code) {
        MutableLiveData<Boolean> result = new MutableLiveData<>();
        Network.getService(context).getFood(code).enqueue(new Callback<NetworkFood>() {
            @Override
            public void onResponse(Call<NetworkFood> call, Response<NetworkFood> response) {
                if (!response.isSuccessful()) { result.postValue(false); } else {
                    insertNetworkFood(response.body());
                    result.postValue(true);
                }
            }

            @Override
            public void onFailure(Call<NetworkFood> call, Throwable t) {
                result.postValue(false);
            }
        });
        return result;
    }

    public void insertNetworkFood(NetworkFood networkFood) {
        insertFood(Converters.getFood(networkFood), Converters.getQuantities(networkFood));
    }

    private static List<Food> foods = new ArrayList<>();
    private static List<Quantity> quantities = new ArrayList<>();
    private static Random random = new Random();
    private static ZonedDateTime localDateTime = new Date().toInstant().atZone( ZoneId.systemDefault());
    private static Date[] dates = {
            Date.from(localDateTime.minusYears(2).toInstant()),
            Date.from(localDateTime.minusYears(1).toInstant()),
            Date.from(localDateTime.minusMonths(4).toInstant()),
            Date.from(localDateTime.minusDays(1).toInstant()),
            Date.from(localDateTime.toInstant())};

    private static String[] names = { "Jus de Fruits", "Biscuits", "Haricots verts" };
    private static String[] brands = { "TropTopFoods", "SuperFoods", "Foods++" };
    private static String[] nutriscores = { "A", "B", "C", "D", "E" };
}