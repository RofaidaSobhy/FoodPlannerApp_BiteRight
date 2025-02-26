package com.example.biteright.data.remote.authentication;

import com.google.firebase.auth.FirebaseUser;

import io.reactivex.rxjava3.core.Single;

public interface FirebaseRemoteDataSource {

    Single<FirebaseUser> createAccountInFirebase(String email, String password);

}
