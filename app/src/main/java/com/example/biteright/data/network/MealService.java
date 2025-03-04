package com.example.biteright.data.network;

import com.example.biteright.data.models.POJO.Root;
import com.example.biteright.data.models.Responses.AreaResponse;
import com.example.biteright.data.models.Responses.CategoryResponse;
import com.example.biteright.data.models.Responses.IngredientResponse;
import com.example.biteright.data.models.Responses.MealResponce;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealService {

    @GET("categories.php")
    Single<CategoryResponse> getCategories();
    @GET("list.php?a=list")
    Single<AreaResponse> getAreas();
    @GET("list.php?i=list")
    Single<IngredientResponse> getIngredients();

    @GET("search.php")
    Single<MealResponce> getMeals(@Query("s") String firstLetters);
}
