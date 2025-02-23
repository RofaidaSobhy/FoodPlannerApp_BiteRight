package com.example.biteright.search.network.categories;

import android.util.Log;
import com.example.biteright.model.Root;
import com.example.biteright.network.CategoriesNetworkCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoriesRemoteDataSourceImpl implements CategoriesRemoteDataSource {
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private static final String TAG = "CategoriesRemoteDataSou";
    CategoriesService  categoriesService;
    private static CategoriesRemoteDataSourceImpl client=null;
    private CategoriesRemoteDataSourceImpl(){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL).build();

        categoriesService = retrofit.create(CategoriesService.class);
    }

    public static CategoriesRemoteDataSourceImpl getInstance(){
        if(client == null){
            client=new CategoriesRemoteDataSourceImpl();
        }
        return client;
    }
    @Override
    public void makeNetworkCall(CategoriesNetworkCallback networkCallback){
        Call<Root> call = categoriesService.getRoot();

        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {

                Log.i(TAG, "onResponse: " + response.body());
                networkCallback.OnSuccessResult(response.body().getCategories());
            }

            @Override
            public void onFailure(Call<Root> call, Throwable throwable) {
                Log.i(TAG, "onFailure: "+ throwable.getMessage());
                networkCallback.OnFailureResult(throwable.getMessage());
            }
        });
    }
}
