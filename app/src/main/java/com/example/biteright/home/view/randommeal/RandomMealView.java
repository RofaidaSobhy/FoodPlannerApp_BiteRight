package com.example.biteright.home.view.randommeal;

import com.example.biteright.model.Meal;

public interface RandomMealView {
    public void showRandomMeal(Meal[] meals);
    public void showErrMsg(String error);

}
