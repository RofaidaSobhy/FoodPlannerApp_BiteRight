package com.example.biteright.home.model.suggestedmeals;

import com.example.biteright.network.NetworkCallback;

public interface SuggestedMealsRepository {
    void getSuggestedMeals(NetworkCallback networkCallback);

}
