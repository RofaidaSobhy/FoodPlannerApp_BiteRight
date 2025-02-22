package com.example.biteright.model;
import com.example.biteright.network.NetworkCallback;
import com.example.biteright.network.RandomMealRemoteDataSource;

public class MealsRepositoryImpl implements MealsRepository {
    RandomMealRemoteDataSource remoteDataSource;
    private static MealsRepositoryImpl repo=null;

    public static MealsRepositoryImpl getInstance(RandomMealRemoteDataSource remoteDataSource){
        if(repo==null){
            repo=new MealsRepositoryImpl(remoteDataSource);
        }
        return repo;
    }

    private MealsRepositoryImpl(RandomMealRemoteDataSource remoteDataSource){
        this.remoteDataSource=remoteDataSource;
    }

    @Override
    public void getRandomMeal(NetworkCallback networkCallback) {
        remoteDataSource.makeNetworkCall(networkCallback);
    }
}
