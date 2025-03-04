package com.example.biteright.data.network;

import com.example.biteright.data.models.POJO.Area;
import com.example.biteright.data.models.POJO.Category;
import com.example.biteright.data.models.POJO.Ingredient;
import com.example.biteright.model.Meal;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealRemoteDataSourceImp implements MealRemoteDataSource{

    private static final String TAG = "MealRemoteDataSourceImp";
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private static Retrofit retrofit;
    private static MealRemoteDataSourceImp instance;
    private static MealService mealService ;

    private MealRemoteDataSourceImp(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        mealService = retrofit.create(MealService.class);
    }
    public static synchronized MealRemoteDataSourceImp getInstance( ) {

        if (instance == null) {
            instance = new MealRemoteDataSourceImp();
        }
        return instance;
    }

    @Override
    public Single<List<Category>> getCategories() {
        return mealService.getCategories()
                .map(categoryResponse -> categoryResponse.getCategories()
                );
    }

    @Override
    public Single<List<Area>> getAreas() {
        return mealService.getAreas()
                .map(areaResponse -> areaResponse.getMeals()
                );
    }

    @Override
    public Single<List<Ingredient>> getIngredients() {
        return mealService.getIngredients()
                .map(ingredientResponse -> ingredientResponse.getMeals()
                );
    }

    @Override
    public Single<List<Meal>> getMeals(String firstLetters) {
        return mealService.getMeals(firstLetters)
                .map(mealResponse -> mealResponse.getMeals());
    }
}
