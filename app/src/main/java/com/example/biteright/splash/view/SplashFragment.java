package com.example.biteright.splash.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.biteright.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SplashFragment extends Fragment {


    private MediaPlayer eating_sound;
    private ImageView logo;
    private TextView txt_slogan;
    private com.airbnb.lottie.LottieAnimationView lottieAnimationView;

    private Handler handler;

    public SplashFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handler= new Handler();

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
        startEatingSound();
        convertLottieWithLogo();
        navigateToNextScreen(view);

    }

    private void initUI(View view){
        logo = view.findViewById(R.id.logo);
        lottieAnimationView = view.findViewById(R.id.lottieAnimationView);
        txt_slogan = view.findViewById(R.id.txt_slogan);
    }

    private void startEatingSound(){

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                eating_sound = MediaPlayer.create(getContext(), R.raw.eatingcookiessound);
                eating_sound.start();
            }
        },1000);


    }



    private void navigateToNextScreen(View view){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                SharedPreferences sharedPreferences= getContext().getSharedPreferences("user_info", Context.MODE_PRIVATE);
                String currentUser=sharedPreferences.getString("email",null);
                if(currentUser == null) {
                    Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_loginFragment);
                }else{
                    Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_homeFragment2);

                }
            }
        },6000);
    }

    private void convertLottieWithLogo() {


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                AlphaAnimation fadeOut = new AlphaAnimation(1, 0);
                fadeOut.setDuration(1000);
                fadeOut.setFillAfter(true);
                lottieAnimationView.startAnimation(fadeOut);
                lottieAnimationView.setVisibility(View.GONE);


                logo.setVisibility(View.VISIBLE);
                txt_slogan.setVisibility(View.VISIBLE);
                AlphaAnimation fadeIn = new AlphaAnimation(0, 1);
                fadeIn.setDuration(1000);
                fadeIn.setFillAfter(true);
                logo.startAnimation(fadeIn);
                txt_slogan.startAnimation(fadeIn);
            }
        },4000);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();


        if (eating_sound != null) {
            eating_sound.stop();
            eating_sound.release();
            eating_sound = null;
        }


        handler.removeCallbacksAndMessages(null);
    }

}