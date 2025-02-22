package com.example.biteright.model;

public class Root{
    public Meal[] meals;

    public Root(Meal[] meals) {
        this.meals = meals;
    }

    public Meal[] getMeals() {
        return meals;
    }

    public void setMeals(Meal[] meals) {
        this.meals = meals;
    }
}
