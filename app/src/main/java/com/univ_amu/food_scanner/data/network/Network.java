package com.univ_amu.food_scanner.data.network;

import android.content.Context;
import com.squareup.moshi.Moshi;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class Network {
    private static Service service = null;

    static public Service getService(Context context) {
        if (service == null) {
            Moshi moshi = new Moshi.Builder().build();
            Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:8000/index.php/")
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .build();
            service = retrofit.create(Service.class);
        }
        return service;
    }
}