package com.example.biteright.network;

import com.example.biteright.data.models.POJO.Category;

import java.util.List;

public interface CategoriesNetworkCallback {
    public void OnSuccessResult(List<Category> categories);
    public void OnFailureResult(String errorMsg);

}
