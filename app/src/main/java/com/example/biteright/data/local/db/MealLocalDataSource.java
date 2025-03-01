package com.example.biteright.data.local.db;

import com.example.biteright.model.Meal;
import com.example.biteright.model.PlannedMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

public interface MealLocalDataSource {
    Completable addMeal(Meal meal);

    Completable deleteMeal(Meal meal);

    Observable<List<Meal>> getMeals();

    Completable addPlannedMeal(PlannedMeal plannedMeal);

    Completable deletePlannedMeal(PlannedMeal plannedMeal);

    Observable<List<PlannedMeal>> getPlannedMeals(String date);
}
