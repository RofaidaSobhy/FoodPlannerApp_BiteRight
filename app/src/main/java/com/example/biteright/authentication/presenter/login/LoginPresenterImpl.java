package com.example.biteright.authentication.presenter.login;

import android.util.Patterns;

import com.example.biteright.authentication.view.login.LoginView;
import com.example.biteright.authentication.view.registration.RegistrationView;

public class LoginPresenterImpl implements LoginPresenter{
    private LoginView _view;

    public LoginPresenterImpl(LoginView _view) {
        this._view = _view;
    }

    @Override
    public void checkDataValidation(String email, String password) {
        boolean isValid;
        isValid = checkLoginDataValidation(email,password);

        if(!isValid){
            _view.validateData(false);
        }else {
            _view.validateData(true);
        }
    }

    private boolean checkLoginDataValidation(String email, String password){
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            _view.validateEmail("Invalid Email");
            return false;
        }

        if(password.length()<6){
            _view.validatePassword("Invalid Password");
            return false;
        }



        return true;
    }
}
