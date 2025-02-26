package com.example.biteright.authentication.presenter;

import android.util.Patterns;

import com.example.biteright.authentication.model.RegistrationRepository;
import com.example.biteright.authentication.view.RegistrationView;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RegistrationPresenterImpl implements RegistrationPresenter{

    private RegistrationView _view;
    private RegistrationRepository _repo;

    public RegistrationPresenterImpl(RegistrationView view, RegistrationRepository repo){
        _view =view;
        _repo = repo;
    }
    @Override
    public void checkDataValidation(String email, String password, String confirmPassword) {
        boolean isValid;
        isValid = checkRegistrationDataValidation(email,password,confirmPassword);

        if(!isValid){
            _view.validateData(false);
        }else {
            _view.validateData(true);
        }

    }

    @Override
    public void createAccountInFirebase(String email, String password) {

       // _view.showLoading(); // Show loading indicator
        _repo.createAccountInFirebase(email, password)
                    .subscribeOn(Schedulers.io()) // Perform task on background thread
                    .observeOn(AndroidSchedulers.mainThread()) // Observe result on main thread
                    .subscribe(new SingleObserver<FirebaseUser>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            // Handle disposable if needed
                        }

                        @Override
                        public void onSuccess(FirebaseUser firebaseUser) {
                            //_view.hideLoading();
                            _view.onRegistrationSuccess(firebaseUser); // Notify the view of success
                        }

                        @Override
                        public void onError(Throwable e) {
                            //_view.hideLoading();
                            _view.onRegistrationFailed(e.getMessage()); // Notify the view of failure
                        }
                    });

    }

    private boolean checkRegistrationDataValidation(String email, String password, String confirmPassword){
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            _view.validateEmail(false, "Invalid Email");
            return false;
        }

        if(password.length()<6){
            _view.validatePassword(false, "Password length should be greater than 6 digits");
            return false;
        }

        if(!password.equals(confirmPassword)){
            _view.validateConfirmPassword(false,"Password not matched");
        }

        return true;
    }
}
