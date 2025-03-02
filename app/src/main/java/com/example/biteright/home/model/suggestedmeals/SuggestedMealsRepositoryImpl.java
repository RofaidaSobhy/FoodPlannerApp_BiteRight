package com.example.biteright.home.model.suggestedmeals;

import com.example.biteright.home.model.randommeal.RandomMealRepository;
import com.example.biteright.home.network.randommeal.RandomMealRemoteDataSource;
import com.example.biteright.home.network.suggestedmeals.SuggestedMealsRemoteDataSource;
import com.example.biteright.network.NetworkCallback;

public class SuggestedMealsRepositoryImpl implements SuggestedMealsRepository {
    SuggestedMealsRemoteDataSource suggestedMealsRemoteDataSource;
    private static SuggestedMealsRepositoryImpl repo=null;

    public static SuggestedMealsRepositoryImpl getInstance(SuggestedMealsRemoteDataSource suggestedMealsRemoteDataSource){
        if(repo==null){
            repo=new SuggestedMealsRepositoryImpl(suggestedMealsRemoteDataSource);
        }
        return repo;
    }

    private SuggestedMealsRepositoryImpl(SuggestedMealsRemoteDataSource suggestedMealsRemoteDataSource){
        this.suggestedMealsRemoteDataSource=suggestedMealsRemoteDataSource;
    }

    @Override
    public void getSuggestedMeals(NetworkCallback networkCallback) {
        suggestedMealsRemoteDataSource.makeNetworkCall(networkCallback);
    }


}