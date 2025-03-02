package com.example.biteright.mealDetails.presenter;


import com.example.biteright.model.Meal;
import com.example.biteright.model.PlannedMeal;

public interface MealDetailsPresenter {
    public void getMealDetails(String mealId);
    public void getIngredients(Meal meal);
    public void addToFav(Meal meal);
    public void addToPlan(PlannedMeal plannedMeal);
}
