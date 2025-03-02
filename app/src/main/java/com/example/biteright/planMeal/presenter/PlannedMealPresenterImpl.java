package com.example.biteright.planMeal.presenter;

import android.util.Log;

import com.example.biteright.data.repo.MealRepository;
import com.example.biteright.favorites.view.FavView;
import com.example.biteright.model.Meal;
import com.example.biteright.model.PlannedMeal;
import com.example.biteright.planMeal.view.PlannedMealsView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PlannedMealPresenterImpl implements PlannedMealPresenter{
    private PlannedMealsView _view;
    private MealRepository _repo;

    public PlannedMealPresenterImpl(PlannedMealsView _view, MealRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    @Override
    public void getPlannedMeals(String date) {

        Observable<List<PlannedMeal>> observable_PlannedMeals =_repo.getStoredPlannedMeals(date);

        observable_PlannedMeals
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> {
                            _view.showData(meals);
                            Log.i("TAG", "getPlannedMeals: "+meals.size());
                        },
                        error -> _view.showErrMsg(error.getMessage())

                );

    }

    @Override
    public void removeFromPlanned(PlannedMeal plannedMeal) {

        Completable completable_PlannedMeal = _repo.deletePlannedMeal(plannedMeal);

        completable_PlannedMeal
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> {
                            Log.i("TAG", "deleteFromFav Successfully: ");
                        },
                        error -> {
                            Log.i("TAG", "deleteFromFav: ");
                            _view.showErrMsg(error.getMessage());
                        }
                );

    }
}
