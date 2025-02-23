package com.example.biteright.search.presenter.categories;

import com.example.biteright.home.model.randommeal.RandomMealRepository;
import com.example.biteright.home.presenter.randommeal.RandomMealPresenter;
import com.example.biteright.home.view.randommeal.RandomMealView;
import com.example.biteright.model.Category;
import com.example.biteright.model.Meal;
import com.example.biteright.network.CategoriesNetworkCallback;
import com.example.biteright.network.NetworkCallback;
import com.example.biteright.search.model.categories.CategoriesRepository;
import com.example.biteright.search.view.categories.CategoriesView;

public class CategoriesPresenterImpl implements CategoriesPresenter, CategoriesNetworkCallback {

    private CategoriesView _view;
    private CategoriesRepository _repo;

    public CategoriesPresenterImpl(CategoriesView view, CategoriesRepository repo){
        _view =view;
        _repo = repo;
    }


    @Override
    public void getCategories() {
        _repo.getCategories(this);
    }


    @Override
    public void OnSuccessResult(Category[] categories) {
        _view.showCategories(categories);
    }

    @Override
    public void OnFailureResult(String errorMsg) {
        _view.showErrMsg(errorMsg);
    }
}
