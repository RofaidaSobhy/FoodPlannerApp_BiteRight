package com.example.biteright.mealDetails.view;

import com.example.biteright.data.models.POJO.Details_Ingredient;
import com.example.biteright.model.Meal;

import java.util.List;

public interface MealDetailsView {
    public void showMealDetails(Meal[] meals);
    public void showErrMsg(String error);

    public void showIngredientsDetails(List<Details_Ingredient> ingredientList);
}
