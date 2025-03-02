package com.example.biteright.home.model.randommeal;
import com.example.biteright.network.NetworkCallback;

public interface RandomMealRepository {
    void getRandomMeal(NetworkCallback networkCallback);

}
