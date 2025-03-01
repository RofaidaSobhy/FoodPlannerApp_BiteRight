package com.example.biteright.data.models.Responses;

import com.example.biteright.data.models.POJO.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class IngredientResponse {
    public List<Ingredient> ingredients;

    public List<Ingredient> getIngredients() {
        return ingredients != null ? ingredients : new ArrayList<>();
    }
}
