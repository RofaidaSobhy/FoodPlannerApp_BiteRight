package com.example.biteright.network;


import com.example.biteright.model.Root;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RandomMealService {

    @GET("random.php")
    Call<Root> getRoot();
}
