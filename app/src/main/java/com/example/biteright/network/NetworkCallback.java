package com.example.biteright.network;


import com.example.biteright.model.Meal;

public interface NetworkCallback {
    public void OnSuccessResult(Meal[] meals);
    public void OnFailureResult(String errorMsg);

}
