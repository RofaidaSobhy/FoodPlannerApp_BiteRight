package com.example.biteright.home.network.suggestedmeals;

import android.util.Log;

import com.example.biteright.data.models.POJO.Root;
import com.example.biteright.network.NetworkCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SuggestedMealsRemoteDataSourceImpl implements SuggestedMealsRemoteDataSource {
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private static final String TAG = "SuggestedMealsRemoteDat";
    SuggestedMealsService suggestedMealsService;
    private static SuggestedMealsRemoteDataSourceImpl client=null;
    private SuggestedMealsRemoteDataSourceImpl(){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL).build();

        suggestedMealsService = retrofit.create(SuggestedMealsService.class);
    }

    public static SuggestedMealsRemoteDataSourceImpl getInstance(){
        if(client == null){
            client=new SuggestedMealsRemoteDataSourceImpl();
        }
        return client;
    }
    @Override
    public void makeNetworkCall(NetworkCallback networkCallback){
        Call<Root> call = suggestedMealsService.getRoot();

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
