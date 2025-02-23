package com.example.biteright.home.view.suggestedmeals;

import com.example.biteright.model.Meal;

public interface SuggestedMealsView {
    public void showSuggestedMeals(Meal[] meals);
    public void showErrMsg(String error);
}
