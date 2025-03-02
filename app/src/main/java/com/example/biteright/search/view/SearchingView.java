package com.example.biteright.search.view;

import com.example.biteright.model.Meal;

import java.util.List;

public interface SearchingView {
    public void showData(List<Meal> meals);
    public void showErrMsg(String error);
}
