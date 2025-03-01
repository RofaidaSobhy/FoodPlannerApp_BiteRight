package com.example.biteright.data.repo;

import com.example.biteright.data.models.POJO.Area;
import com.example.biteright.data.models.POJO.Category;
import com.example.biteright.data.models.POJO.Ingredient;
import com.example.biteright.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public interface MealRepository {
    Single<List<Category>> getCategoryMeals();
    Single<List<Area>> getAreaMeals();
    Single<List<Ingredient>> getIngredientMeals();


    Observable<List<Meal>> getStoredMeals();
    Completable insertMeal(Meal meal);
    Completable deleteMeal(Meal meal);
}
