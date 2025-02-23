package com.example.biteright.mealDetails.presenter;

import com.example.biteright.mealDetails.model.MealDetailsRepository;
import com.example.biteright.mealDetails.view.MealDetailsView;
import com.example.biteright.model.Meal;
import com.example.biteright.network.NetworkCallback;

public class MealDetailsPresenterImpl implements MealDetailsPresenter, NetworkCallback {
    private MealDetailsView _view;
    private MealDetailsRepository _repo;

    public MealDetailsPresenterImpl(MealDetailsView _view, MealDetailsRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    @Override
    public void getMealDetails(String mealId) {
        _repo.getMealDetails(this,mealId);
    }

    @Override
    public void OnSuccessResult(Meal[] meals) {
        _view.showMealDetails(meals);

    }

    @Override
    public void OnFailureResult(String errorMsg) {
        _view.showErrMsg(errorMsg);
    }
}
