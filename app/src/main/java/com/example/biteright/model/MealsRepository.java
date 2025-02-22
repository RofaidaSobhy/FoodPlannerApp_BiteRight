package com.example.biteright.model;
import com.example.biteright.network.NetworkCallback;

public interface MealsRepository {
    void getRandomMeal(NetworkCallback networkCallback);
}
