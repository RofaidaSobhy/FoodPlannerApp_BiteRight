package com.example.biteright.planMeal.presenter;

import com.example.biteright.model.PlannedMeal;

public interface PlannedMealPresenter {
    public void getPlannedMeals(String date);
    public void removeFromPlanned(PlannedMeal plannedMeal);
}
