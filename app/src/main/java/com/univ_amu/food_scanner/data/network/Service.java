package com.univ_amu.food_scanner.data.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Service {
    @GET("/random/{code}")
    Call<NetworkFood> getFood(@Path("code") String code);
}