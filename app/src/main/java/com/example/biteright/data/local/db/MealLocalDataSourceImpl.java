package com.example.biteright.data.local.db;

import android.content.Context;

import com.example.biteright.model.Meal;
import com.example.biteright.model.PlannedMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

public class MealLocalDataSourceImpl implements MealLocalDataSource {
    private FavMealDAO dao;
    private PlannedMealDAO plannedMealDAO;

    public MealLocalDataSourceImpl(Context context){
        FavMealsDataBase db=FavMealsDataBase.getInstance(context);
        dao=db.getMealsDAO();

        PlannedMealsDataBase plannedMealsDataBase = PlannedMealsDataBase.getInstance(context);
        plannedMealDAO = plannedMealsDataBase.getMealsDAO();


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

    @Override
    public Completable addPlannedMeal(PlannedMeal plannedMeal) {
        return plannedMealDAO.insertPlannedMeal(plannedMeal);
    }

    @Override
    public Completable deletePlannedMeal(PlannedMeal plannedMeal) {
        return plannedMealDAO.deletePlannedMeal(plannedMeal);
    }

    @Override
    public Observable<List<PlannedMeal>> getPlannedMeals(String date) {
        return plannedMealDAO.getAllPlannedMeals(date);
    }

}