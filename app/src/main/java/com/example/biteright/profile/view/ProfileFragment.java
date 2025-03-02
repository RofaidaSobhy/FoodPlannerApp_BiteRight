package com.example.biteright.profile.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.biteright.R;


public class ProfileFragment extends Fragment {

    private Button btn_fav;
    private Button btn_plan;
    private Button btn_logout;
    private ImageButton btn_back;
    private TextView txt_email;
    private SharedPreferences sharedPreferences;


    public ProfileFragment() {
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
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI(view);
        sharedPreferences= getActivity().getSharedPreferences("user_info", Context.MODE_PRIVATE);
        txt_email.setText(sharedPreferences.getString("email",""));
        onClick();

    }

    private void initUI(View view){
        btn_fav = view.findViewById(R.id.btn_fav);
        btn_plan = view.findViewById(R.id.btn_plan);
        btn_logout = view.findViewById(R.id.btn_logout);
        txt_email = view.findViewById(R.id.text_email);
        btn_back = view.findViewById(R.id.btn_back_profile);
    }
    private void onClick(){
        btn_fav.setOnClickListener(
                v->{
                    Navigation.findNavController(v).navigate(R.id.action_profileFragment_to_favouriteFragment);

                }
        );

        btn_plan.setOnClickListener(
                v ->{
                    Navigation.findNavController(v).navigate(R.id.action_profileFragment_to_planFragment);

                }
        );

        btn_logout.setOnClickListener(
                v -> {

                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.clear();
                    editor.commit();
                    Navigation.findNavController(v).navigate(R.id.action_profileFragment_to_loginFragment);


                    //delete plan and delete fav


                }
        );
        btn_back.setOnClickListener(
                v -> {
                   NavController navController= Navigation.findNavController(v);
                    navController.popBackStack();
                }
        );

    }
}