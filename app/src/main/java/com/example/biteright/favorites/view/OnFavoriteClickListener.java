package com.example.biteright.favorites.view;

import com.example.biteright.model.Meal;

public interface OnFavoriteClickListener {
    void OnFavoriteClick(Meal meal);
    void OnDeleteClick(Meal meal);
}
