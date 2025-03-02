package com.example.biteright.search.presenter.categories;

import android.util.Log;

import com.example.biteright.data.models.POJO.Category;
import com.example.biteright.data.repo.MealRepository;
import com.example.biteright.search.view.categories.CategoriesView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CategoriesPresenterImpl implements CategoriesPresenter /*, CategoriesNetworkCallback*/ {

    private CategoriesView _view;
    private MealRepository _repo;

    public CategoriesPresenterImpl(CategoriesView view, MealRepository repo){
        _view =view;
        _repo = repo;
    }


    @Override
    public void getCategories() {
        Single<List<Category>> categories_observable = _repo.getCategoryMeals();
        categories_observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        categories -> {
                            Log.i("TAG", "onResponse: " + categories.size());
                            _view.showCategories(categories);

                        },
                        error -> {
                            Log.i("TAG", "onFailure: " + error.getMessage());
                            _view.showErrMsg(error.getMessage());
                        }
                );

    }

}
