package com.example.biteright.data.repo;

import com.example.biteright.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

public interface MealRepository {

    Observable<List<Meal>> getStoredMeals();


    Completable insertMeal(Meal meal);

    Completable deleteMeal(Meal meal);
}
