package com.example.biteright.data.remote.authentication;




import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.rxjava3.core.Single;

public class FirebaseRemoteDataSourceImpl implements FirebaseRemoteDataSource{

    private FirebaseAuth firebaseAuth ;


    public FirebaseRemoteDataSourceImpl(){
        firebaseAuth = FirebaseAuth.getInstance();

    }


    @Override
    public Single<FirebaseUser> createAccountInFirebase(String email, String password) {
        return Single.create(emitter -> firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    emitter.onSuccess(firebaseAuth.getCurrentUser());
                    firebaseAuth.signOut();
                } else {
                    emitter.onError(task.getException());
                }
            })
        );
    }

}
