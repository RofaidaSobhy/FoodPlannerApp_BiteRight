package com.example.biteright.mealDetails.network;

import com.example.biteright.model.Root;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealDetailsService {
    @GET("lookup.php")
    Call<Root> getRoot(@Query("i") String mealId);
}
