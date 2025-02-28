package com.example.biteright.favorites.presenter;

import com.example.biteright.model.Meal;

public interface FavoritePresenter {
    public void getMeals();
    public void removeFromFav(Meal meal);
}