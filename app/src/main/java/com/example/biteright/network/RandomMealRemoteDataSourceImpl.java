package com.example.biteright.network;

import android.util.Log;

import com.example.biteright.model.Root;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RandomMealRemoteDataSourceImpl implements RandomMealRemoteDataSource {
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private static final String TAG = "RandomMealRemoteDataSou";
    RandomMealService randomMealService;
    private static RandomMealRemoteDataSourceImpl client=null;
    private RandomMealRemoteDataSourceImpl(){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL).build();

        randomMealService = retrofit.create(RandomMealService.class);
    }

    public static RandomMealRemoteDataSourceImpl getInstance(){
        if(client == null){
            client=new RandomMealRemoteDataSourceImpl();
        }
        return client;
    }
    @Override
    public void makeNetworkCall(NetworkCallback networkCallback){
        Call<Root> call = randomMealService.getRoot();

        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {

                Log.i(TAG, "onResponse: " + response.body());
                networkCallback.OnSuccessResult(response.body().getMeals());
            }

            @Override
            public void onFailure(Call<Root> call, Throwable throwable) {
                Log.i(TAG, "onFailure: "+ throwable.getMessage());
                networkCallback.OnFailureResult(throwable.getMessage());
            }
        });
    }
}
