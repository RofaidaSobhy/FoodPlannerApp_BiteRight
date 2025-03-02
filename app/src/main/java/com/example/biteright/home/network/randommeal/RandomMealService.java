package com.example.biteright.home.network.randommeal;


import com.example.biteright.data.models.POJO.Root;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RandomMealService {

    @GET("random.php")
    Call<Root> getRoot();
}
