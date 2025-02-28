package com.example.biteright.mealDetails.view;

import com.example.biteright.model.Details_Ingredient;
import com.example.biteright.model.Meal;

import java.util.List;

public interface MealDetailsView {
    public void showMealDetails(Meal[] meals);
    public void showErrMsg(String error);

    public void showIngredientsDetails(List<Details_Ingredient> ingredientList);
}
