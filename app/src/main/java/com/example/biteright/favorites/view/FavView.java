package com.example.biteright.favorites.view;

import com.example.biteright.model.Meal;

import java.util.List;

public interface FavView {
    public void showData(List<Meal> meals);
    public void showErrMsg(String error);
}
