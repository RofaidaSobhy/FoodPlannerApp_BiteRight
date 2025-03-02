package com.example.biteright.authentication.view.login;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.airbnb.lottie.utils.Utils;
import com.example.biteright.R;
import com.example.biteright.authentication.model.registration.RegistrationRepositoryImpl;
import com.example.biteright.authentication.presenter.login.LoginPresenter;
import com.example.biteright.authentication.presenter.login.LoginPresenterImpl;
import com.example.biteright.authentication.presenter.registeration.RegistrationPresenterImpl;
import com.example.biteright.data.remote.authentication.FirebaseRemoteDataSourceImpl;
import com.example.biteright.utilits.NetworkChecker;
import com.example.biteright.utilits.ShowAlert;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginFragment extends Fragment implements LoginView {




    private EditText email;
    private EditText password;
    private Button signIn;
    private Button signInWithGoogle;
    private TextView signUp;
    private Button skip;
    private boolean isValid;
    private LoginPresenter loginPresenter;

    private String str_email;
    private String str_password;
    private View _v;
    private NetworkChecker networkChecker;
    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginPresenter = new LoginPresenterImpl(this);
        networkChecker = new NetworkChecker(getContext());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _v=view;

        initUI(view);
        onClickListener(view);

    }

    private void initUI(View view){
        email = view.findViewById(R.id.editText_Login_Email);
        password = view.findViewById(R.id.editText_Login_Password);
        signIn = view.findViewById(R.id.btn_SignIn);
        signUp = view.findViewById(R.id.doNotHaveAnAccount);
        signInWithGoogle = view.findViewById(R.id.btn_SignIn_With_Google);
        skip = view.findViewById(R.id.btn_skip_Login);

    }

    private void onClickListener(View view){
        signUp.setOnClickListener(
                v -> Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registrationFragment)
        );

        skip.setOnClickListener(
                v -> {
                    Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_homeFragment2);
                }
        );

        signIn.setOnClickListener(
                v-> {
                    LoginToAccount();

                }
        );
    }

    private void LoginToAccount(){
        str_email = email.getText().toString();
        str_password = password.getText().toString();

        loginPresenter.checkDataValidation(
                email.getText().toString(),
                password.getText().toString()
        );


    }
    @Override
    public void validateData(boolean isDataValid) {
        isValid = isDataValid;
        if(!isDataValid){
            return;
        }else{
            //login
            if(networkChecker.isConnected()){
                loginAccountInFireBase(str_email,str_password);

            }else{
                ShowAlert.showAlert(getContext(),"Network Issue!", "No Internet, please connect");

           }
            clearFields();

        }

    }

    private void loginAccountInFireBase(String str_email,String str_password){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(str_email,str_password).addOnCompleteListener(
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            if(firebaseAuth.getCurrentUser().isEmailVerified()){
                                SharedPreferences sharedPreferences= getActivity().getSharedPreferences("user_info", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor=sharedPreferences.edit();
                                editor.putString("email",str_email);
                                editor.putString("password",str_password);
                                editor.commit();
                                Navigation.findNavController(_v).navigate(R.id.action_loginFragment_to_homeFragment2);

                            }else{
                                ShowAlert.showAlert(getContext(),"Warning!","Email not verified,Please verify your email");
                            }

                        }else {

                            //if(networkChecker.isConnected())
                            ShowAlert.showAlert(getContext(),"Login failed!", "Wrong Email and Password");
                            //login failed
                        }

                    }
                }
        );
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
    public void onLoginSuccess(FirebaseUser user) {

    }

    @Override
    public void onLoginFailed(String errorMessage) {

    }
    private void clearFields(){
        email.setText("");
        password.setText("");
    }

    void showAlert(){
        MaterialAlertDialogBuilder builder=new MaterialAlertDialogBuilder(getContext())
                .setTitle("Welcome!")
                .setMessage("Successfully create account, \nCheck email to verify")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
//                        Navigation.findNavController(_v).navigate(R.id.action_registrationFragment_to_homeFragment);
//                        clearFields();

                    }
                });
        //.setIcon(R.drawable.biterightwithoutcalendar);

        builder.create();
        builder.show();

    }
}