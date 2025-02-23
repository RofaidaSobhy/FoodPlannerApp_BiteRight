package com.example.biteright.search.model.categories;

import com.example.biteright.network.CategoriesNetworkCallback;
import com.example.biteright.search.network.categories.CategoriesRemoteDataSource;

public class CategoriesRepositoryImpl implements CategoriesRepository {
    CategoriesRemoteDataSource categoriesRemoteDataSource;
    private static CategoriesRepositoryImpl repo=null;

    public static CategoriesRepositoryImpl getInstance(CategoriesRemoteDataSource categoriesRemoteDataSource){
        if(repo==null){
            repo=new CategoriesRepositoryImpl(categoriesRemoteDataSource);
        }
        return repo;
    }

    private CategoriesRepositoryImpl(CategoriesRemoteDataSource categoriesRemoteDataSource){
        this.categoriesRemoteDataSource=categoriesRemoteDataSource;
    }

    @Override
    public void getCategories(CategoriesNetworkCallback categoriesNetworkCallback) {
        categoriesRemoteDataSource.makeNetworkCall(categoriesNetworkCallback);
    }


}
