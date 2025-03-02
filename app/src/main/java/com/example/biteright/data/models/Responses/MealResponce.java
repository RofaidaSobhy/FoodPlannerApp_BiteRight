package com.example.biteright.data.models.Responses;

import com.example.biteright.data.models.POJO.Ingredient;
import com.example.biteright.model.Meal;

import java.util.ArrayList;
import java.util.List;


public class MealResponce {
    public List<Meal> meals;
    public List<Meal> getMeals() {
        return meals != null ? meals : new ArrayList<>();
    }
}

