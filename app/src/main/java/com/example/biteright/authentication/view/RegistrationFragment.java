package com.example.biteright.authentication.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.biteright.R;
import com.example.biteright.authentication.model.RegistrationRepositoryImpl;
import com.example.biteright.authentication.presenter.RegistrationPresenter;
import com.example.biteright.authentication.presenter.RegistrationPresenterImpl;
import com.example.biteright.data.remote.authentication.FirebaseRemoteDataSource;
import com.example.biteright.data.remote.authentication.FirebaseRemoteDataSourceImpl;
import com.example.biteright.home.model.randommeal.RandomMealRepositoryImpl;
import com.example.biteright.home.network.randommeal.RandomMealRemoteDataSourceImpl;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class RegistrationFragment extends Fragment implements RegistrationView {



    private EditText email;
    private EditText password;
    private EditText confirmPassword;
    private Button signUp;
    private TextView signIn;
    private Button skip;

    private String str_email;
    private String str_password;

    private View _v;

    private RegistrationPresenter registrationPresenter;

    private boolean isValid;


    public RegistrationFragment() {
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        registrationPresenter = new RegistrationPresenterImpl(this, RegistrationRepositoryImpl.getInstance(new FirebaseRemoteDataSourceImpl()));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_registration, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI(view);
        onClickListener(view);

    }

    private void initUI(View view){
        email = view.findViewById(R.id.editText_Registration_Email);
        password = view.findViewById(R.id.editText_Registration_Password);
        confirmPassword = view.findViewById(R.id.editText_Registration_Confirm_Password);
        signIn = view.findViewById(R.id.alreadyHaveAnAccount);
        skip = view.findViewById(R.id.btn_skip_Registration);
        signUp = view.findViewById(R.id.btn_SignUp);

    }

    private void onClickListener(View view){

        _v=view;

        signUp.setOnClickListener(
                v ->{
                    createAccount();
                }
        );

        signIn.setOnClickListener(
                v -> {
                    Navigation.findNavController(view).navigate(R.id.action_registrationFragment_to_loginFragment);
                }
        );

        skip.setOnClickListener(
                v -> {
                    Navigation.findNavController(view).navigate(R.id.action_registrationFragment_to_homeFragment);

                }
        );
    }

    private void createAccount(){
        str_email = email.getText().toString();
        str_password = password.getText().toString();

        registrationPresenter.checkDataValidation(
                email.getText().toString(),
                password.getText().toString(),
                confirmPassword.getText().toString()
        );


    }


    private void clearFields(){
        email.setText("");
        password.setText("");
        confirmPassword.setText("");
    }



    @Override
    public void validateData(boolean isDataValid) {
        isValid = isDataValid;
        if(!isDataValid){
            return;
        }else{
            registrationPresenter.createAccountInFirebase(str_email, str_password);
        }

    }

    @Override
    public void validateEmail(String mes) {

        this.email.setError(mes);

    }

    @Override
    public void validatePassword(String mes) {

        this.password.setError(mes);


    }

    @Override
    public void validateConfirmPassword(String mes) {

        this.confirmPassword.setError(mes);


    }

    @Override
    public void onRegistrationSuccess(FirebaseUser user) {
        Toast.makeText(getContext(),"Succesfully create account, Check email to verify", Toast.LENGTH_SHORT).show();
        user.sendEmailVerification();

        //Navigation.findNavController(_v).navigate(R.id.action_registrationFragment_to_homeFragment);
        showAlert();

    }

    @Override
    public void onRegistrationFailed(String errorMessage) {

    }

    void showAlert(){
        MaterialAlertDialogBuilder builder=new MaterialAlertDialogBuilder(getContext())
                .setTitle("Welcome!")
                .setMessage("Successfully create account, \nCheck email to verify")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Navigation.findNavController(_v).navigate(R.id.action_registrationFragment_to_homeFragment);
                        clearFields();

                    }
                });
                //.setIcon(R.drawable.biterightwithoutcalendar);

        builder.create();
        builder.show();

    }
}