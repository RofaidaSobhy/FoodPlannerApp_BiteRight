package com.example.biteright.mealDetails.network;

import android.util.Log;

import com.example.biteright.model.Root;
import com.example.biteright.network.NetworkCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealDetailsRemoteDataSourceImpl implements MealDetailsRemoteDataSource {
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private static final String TAG = "MealDetailsRemoteDataSo";
    MealDetailsService mealDetailsService;
    private static MealDetailsRemoteDataSourceImpl client=null;
    private MealDetailsRemoteDataSourceImpl(){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL).build();

        mealDetailsService = retrofit.create(MealDetailsService.class);
    }

    public static MealDetailsRemoteDataSourceImpl getInstance(){
        if(client == null){
            client=new MealDetailsRemoteDataSourceImpl();
        }
        return client;
    }
    @Override
    public void makeNetworkCall(NetworkCallback networkCallback , String mealId){
        Call<Root> call = mealDetailsService.getRoot(mealId);

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
