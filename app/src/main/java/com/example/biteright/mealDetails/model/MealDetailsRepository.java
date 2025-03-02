package com.example.biteright.mealDetails.model;

import com.example.biteright.network.NetworkCallback;

public interface MealDetailsRepository {
    void getMealDetails(NetworkCallback networkCallback, String mealId);
}
