package com.example.biteright.favorites.presenter;

import android.util.Log;

import com.example.biteright.data.repo.MealRepository;
import com.example.biteright.favorites.view.FavView;
import com.example.biteright.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavoritePresenterImpl implements FavoritePresenter {

    private FavView _view;
    private MealRepository _repo;

    public FavoritePresenterImpl(FavView view, MealRepository repo){
        _view =view;
        _repo = repo;
    }
    @Override
    public void getMeals() {
        Observable<List<Meal>> observable_product =_repo.getStoredMeals();

        observable_product
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> {
                            _view.showData(meals);
                            Log.i("TAG", "getProducts: "+meals.size());
                        },
                        error -> _view.showErrMsg(error.getMessage())

                );

    }

    @Override
    public void removeFromFav(Meal meal) {
        Completable completable_product = _repo.deleteMeal(meal);

        completable_product
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
