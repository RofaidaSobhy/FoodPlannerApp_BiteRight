package com.example.biteright.mealDetails.network;

import com.example.biteright.network.NetworkCallback;

public interface MealDetailsRemoteDataSource {
    void makeNetworkCall(NetworkCallback networkCallback, String mealId);
}
