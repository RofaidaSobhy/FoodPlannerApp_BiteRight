package com.example.biteright.planMeal.view;

import com.example.biteright.model.PlannedMeal;

import java.util.List;

public interface PlannedMealsView {
    public void showData(List<PlannedMeal> plannedMeals);
    public void showErrMsg(String error);
}
