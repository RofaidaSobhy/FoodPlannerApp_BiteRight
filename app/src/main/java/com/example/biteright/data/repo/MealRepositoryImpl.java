package com.example.biteright.data.repo;

import com.example.biteright.data.local.db.MealLocalDataSource;
import com.example.biteright.data.models.POJO.Area;
import com.example.biteright.data.models.POJO.Category;
import com.example.biteright.data.models.POJO.Ingredient;
import com.example.biteright.data.network.MealRemoteDataSource;
import com.example.biteright.model.Meal;
import com.example.biteright.model.PlannedMeal;

import java.util.ArrayList;
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
    public Single<List<Meal>> getMeals(String firstLetters) {
        return mealRemoteDataSource.getMeals(firstLetters);
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

    @Override
    public Observable<List<PlannedMeal>> getStoredPlannedMeals(String date) {
        return mealLocalDataSource.getPlannedMeals(date);
    }

    @Override
    public Completable insertPlannedMeal(PlannedMeal plannedMeal) {
        return mealLocalDataSource.addPlannedMeal(plannedMeal);
    }

    @Override
    public Completable deletePlannedMeal(PlannedMeal plannedMeal) {
        return mealLocalDataSource.deletePlannedMeal(plannedMeal);
    }


//    @Override
//    public Observable<List<Category>> getCategories(String query) {
//        return Observable.create(emitter -> {
//            try {
//                List<Category> filteredCategories = new ArrayList<>();
//                for (Category category : allCategories) {
//                    if (category.getStrCategory().toLowerCase().contains(query.toLowerCase())) {
//                        filteredCategories.add(category);
//                    }
//                }
//                emitter.onNext(filteredCategories);  // Emit filtered categories
//                emitter.onComplete();
//            } catch (Exception e) {
//                emitter.onError(e);  // Handle errors
//            }
//        });
//    }
}
