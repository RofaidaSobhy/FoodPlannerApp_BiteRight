package com.example.biteright.authentication.view.login;

import com.google.firebase.auth.FirebaseUser;

public interface LoginView {
    public void validateData(boolean isDataValid);
    public void validateEmail(String mes);
    public void validatePassword(String mes);

    void onLoginSuccess(FirebaseUser user);

    void onLoginFailed(String errorMessage);
}
