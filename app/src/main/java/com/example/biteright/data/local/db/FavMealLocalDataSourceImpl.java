package com.example.biteright.data.local.db;

import android.content.Context;

import com.example.biteright.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

public class FavMealLocalDataSourceImpl implements FavMealLocalDataSource {
    private FavMealDAO dao;

    public FavMealLocalDataSourceImpl(Context context){
        FavMealsDataBase db=FavMealsDataBase.getInstance(context);
        dao=db.getMealsDAO();
    }

    @Override
    public Completable addMeal(Meal meal){

        return dao.insertMeal(meal);


    }

    @Override
    public Completable deleteMeal(Meal meal){


        return dao.deleteMeal(meal);

    }

    @Override
    public Observable<List<Meal>> getMeals(){
        return dao.getAllMeals();
    }

}