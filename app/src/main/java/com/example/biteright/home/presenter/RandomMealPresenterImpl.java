package com.example.biteright.home.presenter;

import com.example.biteright.home.view.RandomMealView;
import com.example.biteright.model.Meal;
import com.example.biteright.home.model.RandomMealRepository;
import com.example.biteright.network.NetworkCallback;

public class RandomMealPresenterImpl implements RandomMealPresenter, NetworkCallback {

    private RandomMealView _view;
    private RandomMealRepository _repo;

    public RandomMealPresenterImpl(RandomMealView view, RandomMealRepository repo){
        _view =view;
        _repo = repo;
    }


    @Override
    public void getRandomMeal() {
        _repo.getRandomMeal(this);
    }


    @Override
    public void OnSuccessResult(Meal[] meals) {
        _view.showRandomMeal(meals);
    }

    @Override
    public void OnFailureResult(String errorMsg) {
        _view.showErrMsg(errorMsg);
    }
}
