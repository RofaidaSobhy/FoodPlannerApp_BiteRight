package com.example.biteright.search.view.ingredients;

import com.example.biteright.data.models.POJO.Category;
import com.example.biteright.data.models.POJO.Ingredient;

import java.util.List;

public interface IngredientsView {
    public void showIngredients(List<Ingredient> ingredients);
    public void showErrMsg(String error);
}
