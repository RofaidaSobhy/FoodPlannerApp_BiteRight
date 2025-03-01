package com.example.biteright.data.repo;

import com.example.biteright.data.local.db.MealLocalDataSource;
import com.example.biteright.data.models.POJO.Area;
import com.example.biteright.data.models.POJO.Category;
import com.example.biteright.data.models.POJO.Ingredient;
import com.example.biteright.data.network.MealRemoteDataSource;
import com.example.biteright.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class MealRepositoryImpl implements MealRepository{
    MealLocalDataSource mealLocalDataSource;
    MealRemoteDataSource mealRemoteDataSource;
    private static MealRepositoryImpl repo=null;

    public static MealRepositoryImpl getInstance(MealLocalDataSource mealLocalDataSource, MealRemoteDataSource mealRemoteDataSource){
        if(repo==null){
            repo=new MealRepositoryImpl(mealLocalDataSource, mealRemoteDataSource);
        }
        return repo;
    }

    private MealRepositoryImpl( MealLocalDataSource mealLocalDataSource, MealRemoteDataSource mealRemoteDataSource){
        this.mealLocalDataSource = mealLocalDataSource;
        this.mealRemoteDataSource = mealRemoteDataSource;
    }

    @Override
    public Single<List<Category>> getCategoryMeals() {
        return mealRemoteDataSource.getCategories();
    }

    @Override
    public Single<List<Area>> getAreaMeals() {
        return mealRemoteDataSource.getAreas();
    }

    @Override
    public Single<List<Ingredient>> getIngredientMeals() {
        return mealRemoteDataSource.getIngredients();
    }

    @Override
    public Observable<List<Meal>> getStoredMeals(){
        return mealLocalDataSource.getMeals();
    }

    @Override
    public Completable insertMeal(Meal meal){
        return mealLocalDataSource.addMeal(meal);
    }

    @Override
    public Completable deleteMeal(Meal meal) {
        return mealLocalDataSource.deleteMeal(meal);
    }
}
