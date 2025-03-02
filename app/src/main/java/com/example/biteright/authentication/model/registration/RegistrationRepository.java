package com.example.biteright.authentication.model.registration;

import com.google.firebase.auth.FirebaseUser;

import io.reactivex.rxjava3.core.Single;

public interface RegistrationRepository {
    Single<FirebaseUser> createAccountInFirebase(String email, String password);
}
