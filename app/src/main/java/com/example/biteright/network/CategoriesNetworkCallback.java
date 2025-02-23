package com.example.biteright.network;

import com.example.biteright.model.Category;

public interface CategoriesNetworkCallback {
    public void OnSuccessResult(Category[] categories);
    public void OnFailureResult(String errorMsg);

}
