package com.example.biteright.authentication.view.registration;

import com.google.firebase.auth.FirebaseUser;

public interface RegistrationView {
    public void validateData(boolean isDataValid);
    public void validateEmail(String mes);
    public void validatePassword(String mes);
    public void validateConfirmPassword(String mes);

    void onRegistrationSuccess(FirebaseUser user);

    void onRegistrationFailed(String errorMessage);

}
