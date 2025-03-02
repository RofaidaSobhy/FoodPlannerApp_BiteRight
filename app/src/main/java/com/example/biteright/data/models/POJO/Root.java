package com.example.biteright.data.models.POJO;

import com.example.biteright.model.Meal;

public class Root{
    public Meal[] meals;
    public Category[] categories;


    public Root(Meal[] meals, Category[] categories) {
        this.meals = meals;
        this.categories = categories;
    }

    public Meal[] getMeals() {
        return meals;
    }

    public void setMeals(Meal[] meals) {
        this.meals = meals;
    }

    public Category[] getCategories() {
        return categories;
    }

    public void setCategories(Category[] categories) {
        this.categories = categories;
    }
}
