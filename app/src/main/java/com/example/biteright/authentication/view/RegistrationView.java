package com.example.biteright.authentication.view;

import com.google.firebase.auth.FirebaseUser;

public interface RegistrationView {
    public void validateData(boolean isDataValid);
    public void validateEmail(boolean isEmailValid, String mes);
    public void validatePassword(boolean isPasswordValid, String mes);
    public void validateConfirmPassword(boolean isConfirmPasswordValid, String mes);

    void onRegistrationSuccess(FirebaseUser user);

    void onRegistrationFailed(String errorMessage);

}
