package com.example.biteright.mealDetails.presenter;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.biteright.data.repo.MealRepository;
import com.example.biteright.mealDetails.model.MealDetailsRepository;
import com.example.biteright.mealDetails.view.MealDetailsView;
import com.example.biteright.data.models.POJO.Details_Ingredient;
import com.example.biteright.model.Meal;
import com.example.biteright.network.NetworkCallback;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealDetailsPresenterImpl implements MealDetailsPresenter, NetworkCallback {
    private MealDetailsView _view;
    private MealDetailsRepository _repo;
    private MealRepository mealRepository;

    public MealDetailsPresenterImpl(MealDetailsView _view, MealDetailsRepository _repo, MealRepository mealRepository) {
        this._view = _view;
        this._repo = _repo;
        this.mealRepository= mealRepository;
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



    public void getIngredients(Meal meal){
        List<Details_Ingredient> ingredientList = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            String ingredientName = meal.getIngredient(i);
            String measure = meal.getMeasure(i);
            if (ingredientName != null && !ingredientName.isEmpty()) {
                ingredientList.add(new Details_Ingredient(ingredientName, measure, ingredientName));
            }
        }

        _view.showIngredientsDetails(ingredientList);
    }


    @SuppressLint("CheckResult")
    @Override
    public void addToFav(Meal meal) {
        Completable completable_Product = mealRepository.insertMeal(meal);
        completable_Product
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> {
                            Log.i("TAG", "addToFav Successfully: ");
                        },
                        error -> {
                            Log.i("TAG", "addToFav: ");
                            _view.showErrMsg(error.getMessage());
                        }
                );
    }
}
