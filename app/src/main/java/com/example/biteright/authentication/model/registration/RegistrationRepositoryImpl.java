package com.example.biteright.authentication.model.registration;

import com.example.biteright.data.remote.authentication.FirebaseRemoteDataSource;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.rxjava3.core.Single;


public class RegistrationRepositoryImpl implements RegistrationRepository {

    FirebaseRemoteDataSource firebaseRemoteDataSource;
    private static RegistrationRepositoryImpl repo=null;

    public static RegistrationRepositoryImpl getInstance(FirebaseRemoteDataSource firebaseRemoteDataSource){
        if(repo==null){
            repo=new RegistrationRepositoryImpl(firebaseRemoteDataSource);
        }
        return repo;
    }

    private RegistrationRepositoryImpl(FirebaseRemoteDataSource remoteDataSource){
        this.firebaseRemoteDataSource=remoteDataSource;
    }

    @Override
    public Single<FirebaseUser> createAccountInFirebase(String email, String password) {
        return firebaseRemoteDataSource.createAccountInFirebase(email, password);
    }
}
