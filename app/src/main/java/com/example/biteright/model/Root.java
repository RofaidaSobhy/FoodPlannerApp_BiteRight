package com.example.biteright.model;

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
