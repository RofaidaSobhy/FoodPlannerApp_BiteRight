package com.example.biteright.authentication.presenter.registeration;

public interface RegistrationPresenter {
    public void checkDataValidation(String email, String password, String confirmPassword);
    void createAccountInFirebase(String email, String password);
}
