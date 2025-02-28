package com.example.biteright.mealDetails.presenter;


import com.example.biteright.model.Meal;

public interface MealDetailsPresenter {
    public void getMealDetails(String mealId);
    public void getIngredients(Meal meal);
    public void addToFav(Meal meal);
}
