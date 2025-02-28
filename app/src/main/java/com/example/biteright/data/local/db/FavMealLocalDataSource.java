package com.example.biteright.data.local.db;

import com.example.biteright.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

public interface FavMealLocalDataSource {
    Completable addMeal(Meal meal);

    Completable deleteMeal(Meal meal);

    Observable<List<Meal>> getMeals();
}
