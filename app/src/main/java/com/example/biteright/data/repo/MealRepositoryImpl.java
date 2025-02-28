package com.example.biteright.data.repo;

import com.example.biteright.data.local.db.FavMealLocalDataSource;
import com.example.biteright.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

public class MealRepositoryImpl implements MealRepository{
    FavMealLocalDataSource favMealLocalDataSource;
    private static MealRepositoryImpl repo=null;

    public static MealRepositoryImpl getInstance(FavMealLocalDataSource favMealLocalDataSource){
        if(repo==null){
            repo=new MealRepositoryImpl(favMealLocalDataSource);
        }
        return repo;
    }

    private MealRepositoryImpl( FavMealLocalDataSource favMealLocalDataSource){
        this.favMealLocalDataSource = favMealLocalDataSource;
    }

    @Override
    public Observable<List<Meal>> getStoredMeals(){
        return favMealLocalDataSource.getMeals();
    }



    @Override
    public Completable insertMeal(Meal meal){
        return favMealLocalDataSource.addMeal(meal);
    }

    @Override
    public Completable deleteMeal(Meal meal) {
        return favMealLocalDataSource.deleteMeal(meal);
    }
}
