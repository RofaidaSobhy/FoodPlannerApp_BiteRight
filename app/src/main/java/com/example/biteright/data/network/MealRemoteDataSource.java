package com.example.biteright.data.network;

import com.example.biteright.data.models.POJO.Area;
import com.example.biteright.data.models.POJO.Category;
import com.example.biteright.data.models.POJO.Ingredient;
import com.example.biteright.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface MealRemoteDataSource {
    Single<List<Category>> getCategories();
    Single<List<Area>> getAreas();
    Single<List<Ingredient>> getIngredients();

    Single<List<Meal>> getMeals(String firstLetters);
}
