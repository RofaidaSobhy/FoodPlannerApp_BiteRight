package com.example.biteright.search.presenter.area;

import android.util.Log;

import com.example.biteright.data.models.POJO.Area;
import com.example.biteright.data.models.POJO.Category;
import com.example.biteright.data.repo.MealRepository;
import com.example.biteright.search.view.area.AreaView;
import com.example.biteright.search.view.categories.CategoriesView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AreasPresenterImpl implements AreasPresenter{

    private AreaView _view;
    private MealRepository _repo;

    public AreasPresenterImpl(AreaView _view, MealRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    @Override
    public void getAreas() {
        Single<List<Area>> areas_observable = _repo.getAreaMeals();
        areas_observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        areas -> {
                            Log.i("TAG", "onResponse: " + areas.size());
                            _view.showAreas(areas);

                        },
                        error -> {
                            Log.i("TAG", "onFailure: " + error.getMessage());
                            _view.showErrMsg(error.getMessage());
                        }
                );

    }
}
