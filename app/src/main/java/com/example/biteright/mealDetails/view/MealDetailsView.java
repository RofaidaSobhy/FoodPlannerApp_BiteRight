package com.example.biteright.mealDetails.view;

import com.example.biteright.model.Meal;

public interface MealDetailsView {
    public void showMealDetails(Meal[] meals);
    public void showErrMsg(String error);
}
