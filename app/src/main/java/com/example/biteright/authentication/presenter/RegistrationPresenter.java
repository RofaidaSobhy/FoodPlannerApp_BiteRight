package com.example.biteright.authentication.presenter;

public interface RegistrationPresenter {
    public void checkDataValidation(String email, String password, String confirmPassword);
    void createAccountInFirebase(String email, String password);
}
