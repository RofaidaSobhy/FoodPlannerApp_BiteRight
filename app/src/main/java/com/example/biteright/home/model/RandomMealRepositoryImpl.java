package com.example.biteright.home.model;
import com.example.biteright.network.NetworkCallback;
import com.example.biteright.home.network.RandomMealRemoteDataSource;

public class RandomMealRepositoryImpl implements RandomMealRepository {
    RandomMealRemoteDataSource randomMealRemoteDataSource;
    private static RandomMealRepositoryImpl repo=null;

    public static RandomMealRepositoryImpl getInstance(RandomMealRemoteDataSource randomMealRemoteDataSource){
        if(repo==null){
            repo=new RandomMealRepositoryImpl(randomMealRemoteDataSource);
        }
        return repo;
    }

    private RandomMealRepositoryImpl(RandomMealRemoteDataSource remoteDataSource){
        this.randomMealRemoteDataSource=remoteDataSource;
    }

    @Override
    public void getRandomMeal(NetworkCallback networkCallback) {
        randomMealRemoteDataSource.makeNetworkCall(networkCallback);
    }


}
