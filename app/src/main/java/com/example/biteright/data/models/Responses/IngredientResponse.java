package com.example.biteright.data.models.Responses;

import com.example.biteright.data.models.POJO.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class IngredientResponse {
    public List<Ingredient> meals;

    public List<Ingredient> getMeals() {
        return meals != null ? meals : new ArrayList<>();
    }
}
