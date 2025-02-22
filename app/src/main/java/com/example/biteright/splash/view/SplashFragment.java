package com.example.biteright.splash.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.biteright.R;


public class SplashFragment extends Fragment {

    private TextView txt_splash;

    public SplashFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI(view);
        onClickListener(view);
    }

    private void initUI(View view){
        txt_splash = view.findViewById(R.id.txt_splash);
    }
    private void onClickListener(View view){
        txt_splash.setOnClickListener(
                v -> Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_welcomeFragment)
        );

    }
}