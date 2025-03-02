package com.example.biteright.home.presenter.suggestedmeals;

import com.example.biteright.home.model.suggestedmeals.SuggestedMealsRepository;
import com.example.biteright.home.view.suggestedmeals.SuggestedMealsView;
import com.example.biteright.model.Meal;
import com.example.biteright.network.NetworkCallback;

public class SuggestedMealsPresenterImpl implements SuggestedMealsPresenter, NetworkCallback {

    private SuggestedMealsView _view;
    private SuggestedMealsRepository _repo;

    public SuggestedMealsPresenterImpl(SuggestedMealsView view, SuggestedMealsRepository repo){
        _view =view;
        _repo = repo;
    }


    @Override
    public void getSuggestedMeals() {
        _repo.getSuggestedMeals(this);
    }


    @Override
    public void OnSuccessResult(Meal[] meals) {
        _view.showSuggestedMeals(meals);
    }

    @Override
    public void OnFailureResult(String errorMsg) {
        _view.showErrMsg(errorMsg);
    }
}
