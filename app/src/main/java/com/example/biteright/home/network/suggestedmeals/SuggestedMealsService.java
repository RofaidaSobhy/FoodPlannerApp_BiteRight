package com.example.biteright.home.network.suggestedmeals;

import com.example.biteright.data.models.POJO.Root;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SuggestedMealsService {

    @GET("search.php?f=c")
    Call<Root> getRoot();
}
