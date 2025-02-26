package com.example.biteright.authentication.view;

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

import com.example.biteright.R;


public class LoginFragment extends Fragment {




    private EditText email;
    private EditText password;
    private Button signIn;
    private Button signInWithGoogle;
    private TextView signUp;
    private Button skip;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
    }
}