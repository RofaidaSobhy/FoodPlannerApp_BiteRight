package com.example.biteright.search.network.categories;

import com.example.biteright.model.Root;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoriesService {
    @GET("categories.php")
    Call<Root> getRoot();
}
