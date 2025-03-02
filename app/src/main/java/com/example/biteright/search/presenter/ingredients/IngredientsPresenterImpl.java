package com.example.biteright.search.presenter.ingredients;

import android.util.Log;

import com.example.biteright.data.models.POJO.Category;
import com.example.biteright.data.models.POJO.Ingredient;
import com.example.biteright.data.repo.MealRepository;
import com.example.biteright.search.view.categories.CategoriesView;
import com.example.biteright.search.view.ingredients.IngredientsView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class IngredientsPresenterImpl implements IngredientsPresenter{
    private IngredientsView _view;
    private MealRepository _repo;

    public IngredientsPresenterImpl(IngredientsView _view, MealRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    @Override
    public void getIngredients() {
        Single<List<Ingredient>> ingredients_observable = _repo.getIngredientMeals();
        ingredients_observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        ingredients -> {
                            Log.i("TAG", "onResponse: " + ingredients.size());
                            _view.showIngredients(ingredients);

                        },
                        error -> {
                            Log.i("TAG", "onFailure: " + error.getMessage());
                            _view.showErrMsg(error.getMessage());
                        }
                );

    }
}
