package com.example.biteright.home.presenter;

import com.example.biteright.home.view.RandomMealView;
import com.example.biteright.model.Meal;
import com.example.biteright.model.MealsRepository;
import com.example.biteright.network.NetworkCallback;

public class RandomMealPresenterImpl implements RandomMealPresenter, NetworkCallback {

    private RandomMealView _view;
    private MealsRepository _repo;

    public RandomMealPresenterImpl(RandomMealView view, MealsRepository repo){
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
