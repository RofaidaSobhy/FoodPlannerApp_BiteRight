package com.example.biteright.mealDetails.model;

import com.example.biteright.mealDetails.network.MealDetailsRemoteDataSource;
import com.example.biteright.network.NetworkCallback;

public class MealDetailsRepositoryImpl implements MealDetailsRepository {


    MealDetailsRemoteDataSource mealDetailsRemoteDataSource;
    private static MealDetailsRepositoryImpl repo=null;

    public static MealDetailsRepositoryImpl getInstance(MealDetailsRemoteDataSource mealDetailsRemoteDataSource){
        if(repo==null){
            repo=new MealDetailsRepositoryImpl(mealDetailsRemoteDataSource);
        }
        return repo;
    }

    public MealDetailsRepositoryImpl(MealDetailsRemoteDataSource mealDetailsRemoteDataSource) {
        this.mealDetailsRemoteDataSource = mealDetailsRemoteDataSource;
    }

    @Override
    public void getMealDetails(NetworkCallback networkCallback, String mealId) {
        mealDetailsRemoteDataSource.makeNetworkCall(networkCallback, mealId);
    }

}
